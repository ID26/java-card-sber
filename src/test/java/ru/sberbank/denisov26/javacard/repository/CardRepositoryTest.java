package ru.sberbank.denisov26.javacard.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.denisov26.javacard.models.client.Card;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//@RunWith(SpringRunner.class)
//@SpringBootTest
class CardRepositoryTest {

//    @Autowired
    CardRepository cardRepository;

//    @Test
    public void findAllCardsTodayExpiryDate() throws Exception {
//        LocalDate date = LocalDate.of(2021, 10, 19);
//        List<Card> cards = cardRepository.findAllByExpiryDate(date);
//        cards.forEach(System.out::println);
//        assertThat(cards).hasSize(2);
//
//        assertEquals(date, cards.get(0).getExpiryDate());
//        assertEquals(date, cards.get(cards.size() - 1).getExpiryDate());
    }

}