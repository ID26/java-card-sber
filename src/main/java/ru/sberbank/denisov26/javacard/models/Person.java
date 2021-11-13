package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min=2, max=50, message = "Name mast have from 2 to 50 characters!")
    private String givenName;

    @NotNull
    @Size(min=2, max=50, message = "Surname mast have from 2 to 50 characters!")
    private String surName;

    @NotNull
    @Size(min=2, max=50, message = "Patronymic mast have from 2 to 50 characters!")
    private String patronymic;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Input gender!")
    private Sex sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Input date of birth!")
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
