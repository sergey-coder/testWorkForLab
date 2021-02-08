package ru.korotkov.testforlab.services.impl.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.services.SendMailer;

@Service
public class SendMailerImpl implements SendMailer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    private String subject = "Результат рассмотрения Вашей заявки";

    @Override
    public void sendMail(String toAddress, String messageBody) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(username);
            mailMessage.setTo(toAddress);
            mailMessage.setSubject(subject);
            mailMessage.setText(messageBody);
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}