package ru.sberbank.denisov26.javacard.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sberbank.denisov26.javacard.models.Card;
import ru.sberbank.denisov26.javacard.models.EmailAddress;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static ru.sberbank.denisov26.javacard.utils.CardGenerator.reissuedCard;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {
//    Каждая звездочка в строке cron означает секунды, минуты, часы, дни, месяцы, и дни недели.
//    Вот более подробно. Сейчас значение означает, что проверка будет проходить каждые 10 секунд,
//    это сделано для примера, в дальнейшем мы это поменяем. "*/20 * * * * *" "0 0/05 9 * * *"
    private static final String CRON = "0 0/28 22 * * *";
    private final CardsService cardsService;
    private final EmailAddressService emailAddressService;
    private final EmailServiceImpl emailService;

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        List<Card> cards = cardsService.findAllByExpiryDate(date.plusYears(3));
        List<Card> reissuedCard = new LinkedList<>();
        if (!cards.isEmpty()) {
            cards.forEach(card -> {
                reissuedCard.add(reissuedCard(card));
                try {
                    String message = String.format("Dear %s. Your card %s number %s has expired. The card has been reissued. You can pick it up at any " +
                                    "time convenient for you at the bank branch.", card.getNameOnCard(), card.getCardAssociationName(),card.getCardNumber());

                    List<EmailAddress> emailAddresses = emailAddressService.findAllByClientId(card.getClient().getId());
                    emailAddresses.forEach(emailAddress -> {
                        emailService.send(emailAddress.getEmailAddress(),"Your card has expired. The card has been reissued.", message);
                        log.info("Email have been sent. User id: {}, Date: {}", emailAddress.getClient().getId(), date);
                    });
                } catch (Exception e) {
                    log.error("Email can't be sent.User's id: {}, Error: {}", card.getClient().getId(), e.getMessage());
                    log.error("Email can't be sent", e);
                }
            });
        }

        cardsService.updateAll(reissuedCard);
    }
}