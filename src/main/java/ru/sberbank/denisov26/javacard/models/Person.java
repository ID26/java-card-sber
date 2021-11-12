package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

//    @NotEmpty(message = "Name can't be empty!")
    private String givenName;
//    @NotEmpty(message = "Person not valid")
    private String surName;
//    @NotEmpty(message = "Person not valid")
    private String patronymic;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotEmpty(message = "Person not valid")
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "person", cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private Client client;

    public Person(String givenName, String surName, String patronymic, Sex sex, LocalDate dateOfBirth) {
        this.givenName = givenName;
        this.surName = surName;
        this.patronymic = patronymic;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }
}
