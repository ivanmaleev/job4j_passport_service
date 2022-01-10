package ru.job4j.kafkaclient.service;

import org.springframework.stereotype.Service;
import ru.job4j.kafkaclient.model.Email;

@Service
public class EmailSenderService {
    public void sendEmail(Email email) {
        System.out.println(email.getText());
    }
}
