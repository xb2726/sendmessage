package cn.com.sendmessage.common;


import cn.com.sendmessage.service.EmpService;
import cn.com.sendmessage.service.impl.EmpTxtServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @Auther X .
 */
@Component
public class EmpFactory {
    public EmpService getEmpList(String type) {
        if(type.equalsIgnoreCase("txt")){
            return new EmpTxtServiceImpl();
        }
        return null;
    }
}
