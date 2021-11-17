package ru.sberbank.denisov26.javacard.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.denisov26.javacard.models.Card;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;

    @Test
    void findAllByExpiryDate() {
        List<Card> allByExpiryDate = cardRepository.findAllByExpiryDate(LocalDate.now().plusYears(3));
        assertThat(allByExpiryDate).hasSize(7);
    }

    @Test
    void findAllOrderedByBalance() {
        List<Card> allOrderedByBalance = cardRepository.findAllOrderedByBalance();
        assertThat(allOrderedByBalance).hasSize(7);
        assertThat(allOrderedByBalance.get(6).getBalance()).isEqualTo(4000);
        assertThat(allOrderedByBalance.get(0).getBalance()).isEqualTo(40000);

    }

    @Test
    void setCardById() {
    }
}