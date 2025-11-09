package com.example.notification_service.controller;

import com.example.notification_service.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String email, @RequestParam String text) {
        emailService.sendEmail(email, text);
        return "Письмо отправлено на " + email;
    }
}
