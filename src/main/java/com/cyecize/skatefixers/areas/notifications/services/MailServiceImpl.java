package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final String FROM = "SKATE_FIXERS";

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendMessageToUser(User user, String subject, String message) {
        this.javaMailSender.send(this.createMessage(user.getEmail(), subject, message));
    }

    @Override
    @Async
    public void sendMessageToEmail(String email, String subject, String message) {
        this.javaMailSender.send(this.createMessage(email, subject, message));
    }

    private SimpleMailMessage createMessage(String email, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(FROM);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        return mailMessage;
    }
}
