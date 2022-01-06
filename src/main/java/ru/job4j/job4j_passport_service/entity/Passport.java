package ru.job4j.job4j_passport_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "passport")
@Data
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seria;
    private String number;
    private Timestamp expirationDate;
}
