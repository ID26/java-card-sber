package ru.sberbank.denisov26.javacard.models.client;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;

@Entity
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

    public Card() {
    }

    public Card(String cardAssociationName, String cardNumber, String nameOnCard, String billingAddress, LocalDate validFrom, LocalDate expiryDate, String cardVerificationCode, String dailyWithdrawalLimit, String balance) {
        this.cardAssociationName = cardAssociationName;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.billingAddress = billingAddress;
        this.validFrom = validFrom;
        this.expiryDate = expiryDate;
        this.cardVerificationCode = cardVerificationCode;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardAssociationName() {
        return cardAssociationName;
    }

    public void setCardAssociationName(String cardAssociationName) {
        this.cardAssociationName = cardAssociationName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardVerificationCode() {
        return cardVerificationCode;
    }

    public void setCardVerificationCode(String cardVerificationCode) {
        this.cardVerificationCode = cardVerificationCode;
    }

    public String getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(String dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "\nCard{" +
                "id=" + id +
                ", cardAssociationName='" + cardAssociationName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", validFrom=" + validFrom +
                ", expiryDate=" + expiryDate +
                ", cardVerificationCode='" + cardVerificationCode + '\'' +
                ", dailyWithdrawalLimit='" + dailyWithdrawalLimit + '\'' +
                ", balance='" + balance + '\'' +
                ", client=" + client +
                '}';
    }

    public static Card generateCard(Client client) {
        Card card = new Card();
        card.setCardAssociationName("Visa");
        card.setCardNumber("000000000000" + String.valueOf(new Random().nextInt(8999) + 1000));
        card.setNameOnCard(String.format("%s %s".toUpperCase(), client.getPerson().getGivenName(), client.getPerson().getSurName()));
        card.setBillingAddress(client.getAddresses().stream().findAny().orElse(null).toString());
        card.setValidFrom(LocalDate.now());
        card.setExpiryDate(card.getValidFrom().plusYears(3));
        card.setCardVerificationCode(String.valueOf(new Random().nextInt(899) + 100));
        return card;
    }
}
