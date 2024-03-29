package com.istasyon.backend.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendCommentMessage(String to, String subject, String text, Long employeeId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("istasyon.app@outlook.com");
        message.setTo(to);
        message.setSubject(subject);
        String url = "http://istasyon.online/comment?employeeId=" + employeeId;
        message.setText(text + "\n" + url);
        emailSender.send(message);
    }
}
