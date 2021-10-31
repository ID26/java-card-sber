package ru.sberbank.denisov26.javacard.models.client;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;
}
