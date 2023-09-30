package com.example.gestionbacspalettes.Controller;

import com.example.gestionbacspalettes.Entity.Mouvement;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Service.EmailService;


import com.example.gestionbacspalettes.Service.IExpeditionService;
import com.example.gestionbacspalettes.util.EmailMessage;
import com.example.gestionbacspalettes.util.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController

public class EmailController {

    private final EmailService emailSenderService;
    IFournisseurRepository IFournisseurRepository;


    public EmailController(EmailService emailSenderService) {
        this.emailSenderService = emailSenderService;

    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }
    ///////////////////////////////

    @PostMapping("/sendEmail122")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getRecipient();
        String subject = "Notification Livraison BAC_Palettes " + emailRequest.getName() + "!";
        String body = emailRequest.getMessage();

        emailSenderService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }
    //////////////////////////////
}


