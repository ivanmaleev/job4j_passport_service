package ru.job4j.kafkaclient.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.kafkaclient.model.Email;


@Component
@AllArgsConstructor
public class KafkaTrackerController {

    @KafkaListener(topics = {"email"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        String value = input.value();
        sendEmail(value);
    }

    private void sendEmail(String text) {
        Email email = new Email();
        email.setText(text);
        System.out.println(email);
    }
}