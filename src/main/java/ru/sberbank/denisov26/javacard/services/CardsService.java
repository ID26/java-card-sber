package ru.sberbank.denisov26.javacard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.denisov26.javacard.exceptions.CardNotFoundException;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.repository.CardRepository;

@Service
public class CardsService {

    CardRepository cardRepository;

    @Autowired
    public CardsService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    public Card findById(Long id) throws CardNotFoundException {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card not found!!!"));
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
