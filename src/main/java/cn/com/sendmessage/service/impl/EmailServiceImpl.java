package cn.com.sendmessage.service.impl;

import cn.com.sendmessage.Constant;
import cn.com.sendmessage.common.ChineseCalendar;
import cn.com.sendmessage.common.EmpFactory;
import cn.com.sendmessage.entity.Emp;
import cn.com.sendmessage.service.EmailService;
import cn.com.sendmessage.service.SendMessage;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther X .
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private SendMessage sendMessage;

    @Autowired
    EmpFactory empFactory;

    @Override
    public void sendBatchEmail(String title, String templateName) {
        // 获取txt文件数据
        List<Emp> list = empFactory.getEmpList("txt").getEmp();
        // 生日为今日的emp集合 如果生日为阴历  则转为阳历
        List<Emp> sends = list.stream().filter(k -> isSameDay(k.getBirthday(),k.getBirthdayDateType())).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(sends)) return;
        sends.forEach(emp -> {
            // 这里可能是发邮件 企业微信  短信。。。。
            Map<String, String> map = new HashMap<>();
            map.put("name", emp.getName());
            map.put("date", DateUtil.formatChineseDate(new Date(), false, false));
            if(StrUtil.isNotBlank(emp.getEmail())){
                sendMessage.sendEmail(map, title, templateName, emp.getEmail());
            }

        });
    }

    private boolean isSameDay(Date birthday,String birthdayDateType){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        String today = format.format(new Date());
        if(birthdayDateType!=null &&birthdayDateType.equals(Constant.chineseDate)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthday);
            calendar.set(Calendar.YEAR,DateUtil.thisYear());

            try {
                String date = ChineseCalendar.lunarToSolar(DateUtil.format(calendar.getTime(),"yyyyMMdd"), false);
                return date.substring(5).equals(today);
            } catch (Exception e) {
                log.error("日期类型转换错误",e);
            }
            return false;
        }

        return format.format(birthday).equals(today);
    }

}
