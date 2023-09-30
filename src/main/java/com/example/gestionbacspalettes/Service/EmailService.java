package com.example.gestionbacspalettes.Service;


import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
    public void sendEmail1(String to, String subject, String body) throws MessagingException, javax.mail.MessagingException;

}

