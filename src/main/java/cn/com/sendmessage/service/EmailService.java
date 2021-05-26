package cn.com.sendmessage.service;

import javax.mail.MessagingException;

public interface EmailService {


    void  sendBatchEmail(String title,String templateName);
}
