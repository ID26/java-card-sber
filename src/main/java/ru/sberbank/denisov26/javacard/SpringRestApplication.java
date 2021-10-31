package ru.sberbank.denisov26.javacard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sberbank.denisov26.javacard.models.client.*;
import ru.sberbank.denisov26.javacard.repository.ClientRepository;
import ru.sberbank.denisov26.javacard.repository.PersonRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner {

    final PersonRepository personRepository;
    final ClientRepository clientRepository;

    public SpringRestApplication(PersonRepository personRepository, ClientRepository clientRepository) {
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    public void run(String... args) throws Exception {
//        Address lenin = new Address("Rostov-on-Don", "344001", "Lenin", "100", "3", "45");
//        Address dnepr = new Address("Rostov-on-Don", "344002", "Dneprovskiy", "120", "B", "200");
//        Address druzhby = new Address("Leninavan", "344003", "Druzhby", "50", "5", "");
//
//        Client alena = new Client(new Person("Alena", "Denisova", "Ivanovna", Sex.FEMALE, LocalDate.of(2012, 03, 06)),
//                new Passport("0000", "111111", LocalDate.of(2012, 03, 06), "OBD", "714"),
//                Arrays.asList(new Phone("12345678901")),
//                Arrays.asList(lenin),
//                Arrays.asList(new Email("1111@mail.ru")),
//                Arrays.asList(new Card()));
//        Client nikita = new Client(new Person("Nikita", "Denisov", "Ivanovich", Sex.MALE, LocalDate.of(2010, 02, 01)),
//                new Passport("1111", "222222", LocalDate.of(2010, 02, 01), "OBD", "714"),
//                Arrays.asList(new Phone("23456789012")),
//                Arrays.asList(druzhby),
//                Arrays.asList(new Email("222@mail.ru")),
//                Arrays.asList(new Card()));
//        Client ekaterina = new Client(new Person("Ekaterina", "Bahareva", "Alexandrovna", Sex.FEMALE, LocalDate.of(2002, 10, 26)),
//                new Passport("2222", "333333", LocalDate.of(2002, 10, 26), "OBD", "714"),
//
//                Arrays.asList(new Phone("34567890123")),
//                Arrays.asList(dnepr),
//                Arrays.asList(new Email("333@mail.ru")),
//                Arrays.asList(new Card()));
//
//        Card card1 = Card.generateCard(alena);
//        card1.setExpiryDate(LocalDate.of(2021, 10, 19));
//        Card card2 = Card.generateCard(alena);
//        Card card3 = Card.generateCard(nikita);
//        Card card4 = Card.generateCard(nikita);
//        Card card5 = Card.generateCard(ekaterina);
//        Card card6 = Card.generateCard(ekaterina);
//        Card card7 = Card.generateCard(ekaterina);
//        card7.setExpiryDate(LocalDate.of(2021, 10, 19));
//
//        alena.setCards(Arrays.asList(card1, card2));
//        nikita.setCards(Arrays.asList(card3, card4));
//        ekaterina.setCards(Arrays.asList(card5, card6, card7));
//
//
//        List<Client> clients = Arrays.asList(alena, nikita, ekaterina);
//
//        clientRepository.saveAll(clients);
    }
}
