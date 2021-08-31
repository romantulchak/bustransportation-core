package com.romantulchak.bustransportation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderUtils {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailSenderUtils(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        emailSender.send(email);
    }


}
