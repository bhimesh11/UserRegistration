package com.user.registration.email;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender
{
    private final static Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javamailSender;

    @Override
    @Async
    public void send(String to, String email) {

        try {
            MimeMessage mimeMessage = javamailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("bhimesh@SelfLearning.com");
            javamailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.error("failed to send email",e);
            throw new IllegalStateException("Failed to send an email");
        }

    }
}
