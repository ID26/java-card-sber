package ru.sberbank.denisov26.javacard.models.client;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String givenName;
    private String surName;
    private String patronymic;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "person", cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    private Client client;
}
