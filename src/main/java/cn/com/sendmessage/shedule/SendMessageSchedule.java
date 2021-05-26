package cn.com.sendmessage.shedule;

import cn.com.sendmessage.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther X .
 */
@Component
public class SendMessageSchedule {

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0 7 * * ?")
    void sendEmail(){
        emailService.sendBatchEmail("生日祝福","emailTemp.ftl");
    }
}
