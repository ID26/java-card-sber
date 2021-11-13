package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "person_id")
    @Valid
    private Person person;

//    CascadeType.ALL Действия, применяемые к основному объекту -> применяются к ассоциированному
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "passport_id")
    @Valid
    private Passport passport;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "client_id")
    @Valid
//    @Singular
//    @NotEmpty(message = "Input movie list cannot be empty.")
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "client_id")
    @Valid
    private List<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "client_id")
    @Valid
    private List<EmailAddress> emails;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "client_id")
    private List<Card> cards;

    public Client(Person person, Passport passport, List<Phone> phones, List<Address> addresses, List<EmailAddress> emails, List<Card> cards) {
        this.person = person;
        this.passport = passport;
        this.phones = phones;
        this.addresses = addresses;
        this.emails = emails;
        this.cards = cards;
    }
}
