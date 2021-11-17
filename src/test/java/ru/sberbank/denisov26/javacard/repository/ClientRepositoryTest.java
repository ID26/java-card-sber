package ru.sberbank.denisov26.javacard.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.denisov26.javacard.SpringRestApplication;
import ru.sberbank.denisov26.javacard.models.Client;
import ru.sberbank.denisov26.javacard.models.Passport;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void findAllByExpiryDate() throws Exception{
        assertThat(clientRepository.findAllByExpiryDate(LocalDate.now().plusYears(3))).hasSize(7);
    }

    @Test
    void deleteClient() throws Exception {
        List<Client> clients = clientRepository.findAll();
        clientRepository.delete(clients.get(0));
        int sizeClients = clientRepository.findAll().size();
        assertThat(passportRepository.findAll()).hasSize(sizeClients);
        assertThat(personRepository.findAll()).hasSize(sizeClients);
    }

    @Test
    void deletePassport() throws Error {
        List<Passport> passports = passportRepository.findAll();
        Passport passport = passports.get(0);
        passport.getClient().setPassport(null);
        passportRepository.delete(passport);
        assertThat(passportRepository.findAll()).hasSize(1);
    }
}