package ru.job4j.job4j_passport_service.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport_service.controller.PassportRestTemplateController;
import ru.job4j.job4j_passport_service.entity.Passport;

import java.util.List;

@Service
@AllArgsConstructor
public class ShedulerService {

    private PassportRestTemplateController passportRestTemplateController;
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void checkUnavaliabePassports() {
        List<Passport> unavailable = passportRestTemplateController.findUnavailable();
        for (Passport passport : unavailable) {
            sendEmail(passport.getSeria() + " " + passport.getNumber());
        }
    }

    private void sendEmail(String text) {
        kafkaTemplate.send("email", text);
    }
}
