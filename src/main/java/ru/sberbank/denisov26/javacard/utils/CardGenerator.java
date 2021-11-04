package ru.sberbank.denisov26.javacard.utils;

import ru.sberbank.denisov26.javacard.exceptions.AddressError;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.models.client.Client;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

public class CardGenerator {
    public static Card generateCard(Client client) {
        Card card = new Card();
        StringBuilder cardNumber = new StringBuilder();
        IntStream.range(0, 4).forEach((i) -> cardNumber.append(new Random().nextInt(8999) + 1000));
        card.setCardNumber(cardNumber.toString());
        card.setNameOnCard(String.format("%s %s".toUpperCase(), client.getPerson().getGivenName(), client.getPerson().getSurName()));
        try {
            card.setBillingAddress(client.getAddresses().stream().findAny().orElseThrow(() -> new AddressError("Address error")).toString());
        } catch (AddressError addressError) {
            addressError.printStackTrace();
        }
        card.setValidFrom(LocalDate.now());
        card.setExpiryDate(card.getValidFrom().plusYears(3));
        card.setCardVerificationCode(String.valueOf(new Random().nextInt(899) + 100));
        card.setClient(client);
        return card;
    }
}
