package ru.sberbank.denisov26.javacard.models.client;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    //    @NotEmpty(message = "Card Association Name can't be empty!")
    private String cardAssociationName;
    private String cardNumber;
    private String nameOnCard;
    private String billingAddress;
    private LocalDate validFrom;
    private LocalDate expiryDate;
    private String cardVerificationCode;
    //    @Positive(message = "Daily Withdrawal Limit can't be negative!")
    private String dailyWithdrawalLimit;
    //    @Positive(message = "Balance can't be negative!")
    private String balance;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;


//    public static Card generateCard(Client client) {
//        Card card = new Card();
//        card.setCardAssociationName("Visa");
//        card.setCardNumber("000000000000" + String.valueOf(new Random().nextInt(8999) + 1000));
//        card.setNameOnCard(String.format("%s %s".toUpperCase(), client.getPerson().getGivenName(), client.getPerson().getSurName()));
//        card.setBillingAddress(client.getAddresses().stream().findAny().orElse(null).toString());
//        card.setValidFrom(LocalDate.now());
//        card.setExpiryDate(card.getValidFrom().plusYears(3));
//        card.setCardVerificationCode(String.valueOf(new Random().nextInt(899) + 100));
//        return card;
//    }
}
