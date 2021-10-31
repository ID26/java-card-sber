package ru.sberbank.denisov26.javacard.models.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String postCode;
    private String street;
    private String building;
    private String extension;
    private String apartment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;

}
