package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    //    @NotEmpty(message = "Card Association Name can't be empty!")
    @Enumerated(EnumType.STRING)
    private CardAssociation cardAssociationName;
    private String cardNumber;
    private String nameOnCard;
    private String billingAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    private String cardVerificationCode;
    @Positive(message = "Daily Withdrawal Limit can't be negative!")
//    @NotEmpty(message = "Daily Withdrawal Limit can't be empty!")
    private Long dailyWithdrawalLimit;
    @Positive(message = "Balance can't be negative!")
//    @NotEmpty(message = "Balance can't be empty!")
    private Long balance;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;
}
