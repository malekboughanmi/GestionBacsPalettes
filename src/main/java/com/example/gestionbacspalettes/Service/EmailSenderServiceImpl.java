package com.example.gestionbacspalettes.Service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("benslamaayatoutou@gmail.com");
      //  simpleMailMessage.setTo(to);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }

    public void sendEmail1(String to, String subject, String body) throws MessagingException, javax.mail.MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

}