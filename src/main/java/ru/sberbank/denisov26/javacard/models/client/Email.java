package ru.sberbank.denisov26.javacard.models.client;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Email {
    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

}
