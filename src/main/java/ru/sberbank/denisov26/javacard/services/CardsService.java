package ru.sberbank.denisov26.javacard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.denisov26.javacard.exceptions.CardNotFoundException;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.repository.CardRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardsService {

    private final CardRepository cardRepository;

//    @Autowired
//    public CardsService(CardRepository cardRepository) {
//        this.cardRepository = cardRepository;
//    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    public Card findById(Long id) throws CardNotFoundException {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card not found!!!"));
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    public List<String> findAllByExpiryDate(LocalDate date){
        return cardRepository.findAllByExpiryDate(date);
    }
    public List<Card> findAllCardsByExpiryDate(LocalDate date){
        return cardRepository.findAllCardsByExpiryDate(date);
    }


    @Transactional
    public void update(Long id, Card card) {
//        try {
//            Card cardForUpdate = findById(id);
//            if (cardForUpdate == null) return;
//        } catch (CardNotFoundException e) {
//            System.err.println(e);
//        }
        cardRepository.setCardById(id, card.getCardAssociationName(), card.getCardNumber(),
                card.getNameOnCard(), card.getBillingAddress(), card.getValidFrom(), card.getExpiryDate(),
                card.getCardVerificationCode(), card.getDailyWithdrawalLimit(), card.getBalance());
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }
}
