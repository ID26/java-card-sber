package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailAddress {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 250, message = "Email mast have from 2 to 250 characters!")
    @Email(message = "Email don't valid!")
    private String emailAddress;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}