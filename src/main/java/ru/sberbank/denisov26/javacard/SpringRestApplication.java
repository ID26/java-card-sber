package ru.sberbank.denisov26.javacard;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.sberbank.denisov26.javacard.models.*;
import ru.sberbank.denisov26.javacard.services.CardsService;
import ru.sberbank.denisov26.javacard.services.ClientsService;
import ru.sberbank.denisov26.javacard.services.EmailAddressService;
import ru.sberbank.denisov26.javacard.services.EmailServiceImpl;
import ru.sberbank.denisov26.javacard.utils.CardGenerator;

import java.time.LocalDate;
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
        Address omsk = new Address("Omsk", "344001", "Lenin", "100", "3", "45");
        Address voronezh = new Address("Voronezh", "344002", "Dneprovskiy", "120", "B", "200");
        Address krasnodar = new Address("Krasnodar", "344003", "Druzhby", "50", "5", "");
        Address moscow = new Address("Moscow", "344004", "Lenin", "100", "3", "45");
        Address rostov = new Address("Rostov-on-Don", "344005", "Dneprovskiy", "120", "B", "200");
        Address samara = new Address("Samara", "344006", "Druzhby", "50", "5", "");

        Person personAlena = new Person("Alena", "Denisova", "Ivanovna", Sex.FEMALE, LocalDate.of(2012, 03, 06));
        Person personNikita = new Person("Nikita", "Denisov", "Ivanovich", Sex.MALE, LocalDate.of(2010, 02, 01));
        Person personKate = new Person("Ekaterina", "Bahareva", "Alexandrovna", Sex.FEMALE, LocalDate.of(2002, 10, 26));

        Passport passportAlena = new Passport("0000", "111111",
                LocalDate.of(2012, 03, 06), "OBD", "714711");
        Passport passportNikita = new Passport("1111", "222222",
                LocalDate.of(2010, 02, 01), "OBD", "714712");
        Passport passportKate = new Passport("2222", "333333",
                LocalDate.of(2002, 10, 26), "OBD", "714714");

        Client alena = new Client(personAlena, passportAlena,
                Arrays.asList(new Phone("12345678901"), new Phone("12345678901")),
                Arrays.asList(omsk, moscow),
                Arrays.asList(new EmailAddress("ivandenisov26@mail.ru"), new EmailAddress("sigma1978@bk.ru")),
                Arrays.asList(new Card()));
        Client nikita = new Client(personNikita, passportNikita,
                Arrays.asList(new Phone("23456789012"), new Phone("23456789012")),
                Arrays.asList(krasnodar, samara),
                Arrays.asList(new EmailAddress("workpost26@gmail.com"), new EmailAddress("2009november2009@gmail.com")),
                Arrays.asList(new Card()));
        Client ekaterina = new Client(personKate, passportKate,
                Arrays.asList(new Phone("34567890123"), new Phone("34567890123")),
                Arrays.asList(voronezh, rostov),
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

        card1.setDailyWithdrawalLimit(1L);
        card2.setDailyWithdrawalLimit(2L);
        card3.setDailyWithdrawalLimit(3L);
        card4.setDailyWithdrawalLimit(4L);
        card5.setDailyWithdrawalLimit(5L);
        card6.setDailyWithdrawalLimit(6L);
        card7.setDailyWithdrawalLimit(7L);

        card1.setBalance(4000L);
        card2.setBalance(4000L);
        card3.setBalance(40000L);
        card4.setBalance(40000L);
        card5.setBalance(40000L);
        card6.setBalance(40000L);
        card7.setBalance(40000L);

        alena.setCards(Arrays.asList(card1, card2));
        nikita.setCards(Arrays.asList(card3, card4));
        ekaterina.setCards(Arrays.asList(card5, card6, card7));


        List<Client> clients = Arrays.asList(alena, nikita, ekaterina);

        clientsService.saveAll(clients);

    }
}
