package ru.sberbank.denisov26.javacard.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.denisov26.javacard.models.Card;
import ru.sberbank.denisov26.javacard.repository.CardRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class CardsServiceTest {

    @Autowired
    CardRepository cardRepository;

    @BeforeEach
    void setParameter() {

    }

    @Test
    void findAllByExpiryDate() {
        cardRepository.findAllByExpiryDate(LocalDate.now().plusYears(3))
                .forEach(card -> System.out.println(card));
    }

    @Test
    void update(/*Long id, Card cardRepository*/) {
//        if (card == null) {
//            List<Card> allCards = cardRepository.findAll();
//
//            if (allCards == null || allCards.isEmpty()) {
//                return;
//            }
//            card = allCards.get(0);
//            id = card.getId();
//        }
//
//        cardRepository.setCardById(id, card.getCardAssociationName(), card.getCardNumber(),
//                card.getNameOnCard(), card.getBillingAddress(), card.getValidFrom(), card.getExpiryDate(),
//                card.getCardVerificationCode(), card.getDailyWithdrawalLimit(), card.getBalance());
    }

    @Test
    void updateAll() {
//        List<Card> allCards = cardRepository.findAll();
//
//        if (allCards == null || allCards.isEmpty()) {
//            return;
//        }
//
//        allCards.forEach(card -> update(card.getId(), card));
    }
}