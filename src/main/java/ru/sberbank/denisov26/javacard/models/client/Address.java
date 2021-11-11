package ru.sberbank.denisov26.javacard.models.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String city;
    private String postCode;
    private String street;
    private String building;
    private String extension;
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
