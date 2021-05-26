package cn.com.sendmessage.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther X .
 */
@Slf4j
public class FileTransformObjectUtils {

    public static List fileTransformObject(String url, Class clazz){
        List list = new ArrayList();
        try {
            // 读入TXT文件
            File filename = ResourceUtils.getFile(url);
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            Field[] fields = clazz.getDeclaredFields();
            while (line != null) {
                if(fields.length==5){
                    Object object = clazz.newInstance();
                    // 一次读入一行数据
                    String[] split = line.split("\\s+", -1);
                    for (int j = 0; j < fields.length; j++) {
                        Field field = fields[j];

                        field.setAccessible(true);
                        //判断对象属性类型 如果类型为Integer类型转换为Integer类型赋值
                        //如果不是Integer类型转换为其他string类型赋值
                        if (field.getType() == Integer.class) {
                            field.set(object, new Integer(split[j].toString().trim()));
                        } else if (field.getType() == Double.class) {
                            field.set(object, new Double(split[j].toString().trim()));
                        } else if (field.getType() == Date.class) {
                            field.set(object, new SimpleDateFormat("yyyy-MM-dd").parse(split[j].toString().trim()));
                        } else {
                            field.set(object, split[j].toString());
                        }

                    }

                    list.add(object);
                }

                line = br.readLine();
                if (line==null){
                    break;
                }
            }
        } catch (Exception e) {
            log.error("txt文件转换为list失败",e);
        }
        return list;
    }

}
