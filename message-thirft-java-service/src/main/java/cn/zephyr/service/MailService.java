package cn.zephyr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    public void sendEmail(String email,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人邮箱
        message.setFrom(mailProperties.getUsername());
        // 收信人邮箱
        message.setTo(email);
        // 邮件主题
        message.setSubject("简单邮件测试");
        // 邮件内容
        message.setText(content);
        this.javaMailSender.send(message);

    }
}
