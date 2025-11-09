package com.example.notification_service.kafka;

import com.example.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final EmailService emailService;

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("📩 Получено сообщение из Kafka: " + message);

        if (message.startsWith("create:")) {
            String email = message.split(":")[1];
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт успешно создан.");
        } else if (message.startsWith("delete:")) {
            String email = message.split(":")[1];
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт был удалён.");
        }
    }
}
