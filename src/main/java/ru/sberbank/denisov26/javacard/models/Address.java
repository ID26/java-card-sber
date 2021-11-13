package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=2, max=250, message = "Enter valid city where have from 2 to 250 characters!")
    private String city;

    @NotNull
    @Size(min=6, max=6, message = "Enter valid post code where have 6 digits!")
    private String postCode;

    @NotNull
    @Size(min=2, max=250, message = "Enter valid street where have from 2 to 250 characters!")
    private String street;

    @NotNull
    @Size(min=1, max=4, message = "Enter valid building number from 1 to 4 digits!")
    private String building;

    @Size(max=250, message = "Enter valid extension where have max 250 characters!")
    private String extension;

    @Size(max=4, message = "Enter valid apartment number have max 4 digits!")
    private String apartment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    public Address(String city, String postCode, String street, String building, String extension, String apartment) {
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.apartment = apartment;
    }
}
