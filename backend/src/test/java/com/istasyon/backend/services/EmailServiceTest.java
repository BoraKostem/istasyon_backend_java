package com.istasyon.backend.services;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Test
    void sendSimpleMessage() {
        // Arrange
        JavaMailSender emailSender = mock(JavaMailSender.class);
        EmailService emailService = new EmailService(emailSender);

        // Act
        emailService.sendCommentMessage("forumoyun2@gmail.com", "Test", "This is a test", 1L);

        // Assert
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendRealEmail() throws Exception {
        // Load properties from application.properties
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream("src/main/resources/application.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Arrange
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(properties.getProperty("spring.mail.host"));
        emailSender.setPort(Integer.parseInt(properties.getProperty("spring.mail.port")));
        emailSender.setUsername(properties.getProperty("spring.mail.username"));
        emailSender.setPassword(properties.getProperty("spring.mail.password"));
        emailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.required", properties.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

        EmailService emailService = new EmailService(emailSender);

        // Act
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("forumoyun2@gmail.com");
        helper.setSubject("Test");
        helper.setText("This is a test", true);
        emailService.sendCommentMessage("forumoyun2@gmail.com", "Test", "This is a test", 1L);

        // There's no assert here because we're sending a real email
        // If the email fails to send, an exception will be thrown
    }
}