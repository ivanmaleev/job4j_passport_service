package ru.job4j.kafkaclient.model;

import lombok.Data;

@Data
public class Email {
    private String text;
    private String address;
}
