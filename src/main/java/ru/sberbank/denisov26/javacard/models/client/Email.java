package ru.sberbank.denisov26.javacard.models.client;

import javax.persistence.*;


@Entity
public class Email {
    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public Email() {
    }

    public Email(@javax.validation.constraints.Email(message = "Email don't valid!") String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "\nEmail{" +
                "id=" + id +
                ", email='" + emailAddress + '\'' +
                ", client=" + client +
                '}';
    }
}
