package cn.com.sendmessage.service.impl;

import cn.com.sendmessage.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    EmailService emailService;
    @Test
    public void sendBatchEmail() {
        emailService.sendBatchEmail("生日快乐","emailTemp.ftl");
        System.out.println("发送完成");
    }
}
