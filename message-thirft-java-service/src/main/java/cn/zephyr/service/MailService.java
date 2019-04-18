package cn.zephyr.service;


import cn.zephyr.thrift.message.MessageService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService implements MessageService.Iface {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    public void sendEmail(String email,String content){


    }

    @Override
    public boolean sendMobileMessage(String mobile, String message) throws TException {
        System.err.println("模拟发送短信");
        return true;
    }

    @Override
    public boolean sendEmailMessage(String email, String message) throws TException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 发件人邮箱
        mailMessage.setFrom(mailProperties.getUsername());
        // 收信人邮箱
        mailMessage.setTo(email);
        // 邮件主题
        mailMessage.setSubject("简单邮件测试");
        // 邮件内容
        mailMessage.setText(message);
        this.javaMailSender.send(mailMessage);
        return false;
    }
}
