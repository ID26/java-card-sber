package ru.sberbank.denisov26.javacard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sberbank.denisov26.javacard.models.client.*;
import ru.sberbank.denisov26.javacard.services.ClientsService;
import ru.sberbank.denisov26.javacard.utils.CardGenerator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner {

    final ClientsService clientsService;

    @Autowired
    public SpringRestApplication(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

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
                Arrays.asList(new EmailAddress("1111@mail.ru"), new EmailAddress()),
                Arrays.asList(new Card()));
        Client nikita = new Client(personNikita, passportNikita, Arrays.asList(new Phone("23456789012"), new Phone()),
                Arrays.asList(druzhby, new Address()),
                Arrays.asList(new EmailAddress("222@mail.ru"), new EmailAddress()),
                Arrays.asList(new Card()));
        Client ekaterina = new Client(personKate, passportKate,
                Arrays.asList(new Phone("34567890123"), new Phone()),
                Arrays.asList(dnepr, new Address()),
                Arrays.asList(new EmailAddress("333@mail.ru"), new EmailAddress()),
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
    }
}
