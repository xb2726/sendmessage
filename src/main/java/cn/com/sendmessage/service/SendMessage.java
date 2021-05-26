package cn.com.sendmessage.service;

/**
 * @Auther X .
 */
public interface SendMessage {
    void sendEmail(Object params, String title, String templateName, String to);
    // ================还可以有其他发送方式===============
}
