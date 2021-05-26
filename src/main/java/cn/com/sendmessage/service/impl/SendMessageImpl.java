package cn.com.sendmessage.service.impl;

import cn.com.sendmessage.service.SendMessage;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther X .
 */
@Component
@Slf4j
public class SendMessageImpl implements SendMessage {

    //发送者
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSenderImpl mailSender;
    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfig freeMarkerConfigurer;

    @Override
    public void sendEmail(Object params, String title, String templateName, String to) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(InternetAddress.parse(to));
            mimeMessageHelper.setSubject("【" + title + "】");
            Map<String, Object> model = new HashMap<>();
            model.put("params", params);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("发送邮件失败", e);
        }
    }
}
