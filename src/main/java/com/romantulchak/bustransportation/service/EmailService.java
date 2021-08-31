package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.model.email.AbstractEmail;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(AbstractEmail abstractEmail) throws MessagingException;
}
