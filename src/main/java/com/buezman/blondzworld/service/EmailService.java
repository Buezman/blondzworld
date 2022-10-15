package com.buezman.blondzworld.service;

import javax.mail.MessagingException;

public interface EmailService {
//    void sendSimpleMessage(String to, String subject, String text);
    void sendEmail(String to, String subject, String text);
}
