package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.exception.AppApiException;
import com.buezman.blondzworld.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setFrom("Blondzworld");
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AppApiException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }

    }
}
