package ru.sberbank.denisov26.javacard;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.sberbank.denisov26.javacard.models.client.*;
import ru.sberbank.denisov26.javacard.repository.EmailAddressRepository;
import ru.sberbank.denisov26.javacard.services.CardsService;
import ru.sberbank.denisov26.javacard.services.ClientsService;
import ru.sberbank.denisov26.javacard.services.EmailAddressService;
import ru.sberbank.denisov26.javacard.services.EmailServiceImpl;
import ru.sberbank.denisov26.javacard.utils.CardGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpringRestApplication implements CommandLineRunner {

    private final ClientsService clientsService;
    private final CardsService cardsService;
    private final EmailAddressService emailAddressService;

    private final EmailServiceImpl emailSender;


    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Address lenin = new Address("Rostov-on-Don", "344001", "Lenin", "100", "3", "45");
        Address dnepr = new Address("Rostov-on-Don", "344002", "Dneprovskiy", "120", "B", "200");
        Address druzhby = new Address("Leninavan", "344003", "Druzhby", "50", "5", "");

        Person personAlena = new Person("Alena", "Denisova", "Ivanovna", Sex.FEMALE, LocalDate.of(2012, 03, 06));
        Person personNikita = new Person("Nikita", "Denisov", "Ivanovich", Sex.MALE, LocalDate.of(2010, 02, 01));
        Person personKate = new Person("Ekaterina", "Bahareva", "Alexandrovna", Sex.FEMALE, LocalDate.of(2002, 10, 26));

        Passport passportAlena = new Passport("0000", "111111",
                LocalDate.of(2012, 03, 06), "OBD", "714");
        Passport passportNikita = new Passport("1111", "222222",
                LocalDate.of(2010, 02, 01), "OBD", "714");
        Passport passportKate = new Passport("2222", "333333",
                LocalDate.of(2002, 10, 26), "OBD", "714");

        Client alena = new Client(personAlena, passportAlena, Arrays.asList(new Phone("12345678901"), new Phone()),
                Arrays.asList(lenin, new Address()),
                Arrays.asList(new EmailAddress("ivandenisov26@mail.ru"), new EmailAddress("pr0j6ct@yandex.com")),
                Arrays.asList(new Card()));
        Client nikita = new Client(personNikita, passportNikita, Arrays.asList(new Phone("23456789012"), new Phone()),
                Arrays.asList(druzhby, new Address()),
                Arrays.asList(new EmailAddress("workpost26@gmail.com"), new EmailAddress("pr0j6ct@yandex.com")),
                Arrays.asList(new Card()));
        Client ekaterina = new Client(personKate, passportKate,
                Arrays.asList(new Phone("34567890123"), new Phone()),
                Arrays.asList(dnepr, new Address()),
                Arrays.asList(new EmailAddress("ivandenisov1979@yandex.ru"), new EmailAddress("pr0j6ct@yandex.com")),
                Arrays.asList(new Card()));

        Card card1 = CardGenerator.generateCard(alena);
        Card card2 = CardGenerator.generateCard(alena);
        Card card3 = CardGenerator.generateCard(nikita);
        Card card4 = CardGenerator.generateCard(nikita);
        Card card5 = CardGenerator.generateCard(ekaterina);
        Card card6 = CardGenerator.generateCard(ekaterina);
        Card card7 = CardGenerator.generateCard(ekaterina);

        card1.setCardAssociationName(CardAssociation.VISA);
        card2.setCardAssociationName(CardAssociation.MASTER_CARD);
        card3.setCardAssociationName(CardAssociation.MIR);
        card4.setCardAssociationName(CardAssociation.MASTER_CARD);
        card5.setCardAssociationName(CardAssociation.VISA);
        card6.setCardAssociationName(CardAssociation.MASTER_CARD);
        card7.setCardAssociationName(CardAssociation.MIR);


        alena.setCards(Arrays.asList(card1, card2));
        nikita.setCards(Arrays.asList(card3, card4));
        ekaterina.setCards(Arrays.asList(card5, card6, card7));


        List<Client> clients = Arrays.asList(alena, nikita, ekaterina);

        clientsService.saveAll(clients);

//        List<Card> list = cardsService.findAllCardsByExpiryDate(LocalDate.now().plusYears(3));
//        List<String> messages = new ArrayList<>();
//        if (!list.isEmpty()) {
//            list.forEach(card -> {
//                try {
//                    String message = String.format("Dear %s. Your card %s number %s has expired. " +
//                            "The card has been reissued. " +
//                            "You can pick it up at any time convenient for you at the bank branch.",
//                            card.getNameOnCard(), card.getCardAssociationName(),card.getCardNumber());
//
//
//                    List<EmailAddress> emailAddresses = emailAddressService.findAllByClientId(card.getClient().getId());
//                    emailAddresses.forEach(emailAddress -> {
//                        messages.add(message + " " + emailAddress.getEmailAddress());
//                    });
//
////                    emailService.send(client.getEmail(), "Happy Birthday!", message);
////                    log.info("Email have been sent. User id: {}, Date: {}", client.getId(), date);
//                } catch (Exception e) {
//                    System.out.println("Not found" + e);
////                    log.error("Email can't be sent.User's id: {}, Error: {}", client.getId(), e.getMessage());
////                    log.error("Email can't be sent", e);
//                }
//            });
//        }
//
//        messages.forEach(System.out::println);

    }
}
