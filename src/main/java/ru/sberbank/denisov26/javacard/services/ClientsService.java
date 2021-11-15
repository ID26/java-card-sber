package ru.sberbank.denisov26.javacard.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.exceptions.PassportError;
import ru.sberbank.denisov26.javacard.models.Client;
import ru.sberbank.denisov26.javacard.models.Passport;
import ru.sberbank.denisov26.javacard.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientsService {
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final EmailAddressRepository emailAddressRepository;
    private final PassportRepository passportRepository;
    private final PhoneRepository phoneRepository;

    public List<Client> findAllCustomersWithCardExpirationDates(LocalDate date) {
        return clientRepository.findAllByExpiryDate(date);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found!!!"));
    }

    public Client save(Client client) {
        Passport passport = passportRepository.findPassportByPassportSeriesAndPassportNumber(
                client.getPassport().getPassportSeries(), client.getPassport().getPassportNumber());
        if (passport != null) {
            return passport.getClient();
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void update(Long id, Client client) throws PassportError {
        checkPassport(client);

//        Чтоб потом не забыл, из шаблона приходят только данные из форм (набор аргументов, а не сущьности),
//        по этому нам нужен объект из базы, чтоб брать у него id составных сущностей
        try {
            Client updateClient = findById(id);
            if (updateClient == null) return;
            if (updateClient.getPerson() != null) {
                personRepository.setPersonById(updateClient.getPerson().getId(), client.getPerson().getSurName(), client.getPerson().getGivenName(),
                        client.getPerson().getPatronymic(), client.getPerson().getDateOfBirth(), client.getPerson().getSex());
            }
            if (updateClient.getPassport() != null) {
                passportRepository.setPassportById(updateClient.getPassport().getId(), client.getPassport().getPassportSeries(),
                        client.getPassport().getPassportNumber(), client.getPassport().getIssueDate(), client.getPassport().getIssueDepartment(),
                        client.getPassport().getCodDepartment());
            }
            if (updateClient.getPhones() != null) {
                IntStream.range(0, client.getPhones().size()).forEach(i ->
                        phoneRepository.setPhoneById(updateClient.getPhones().get(i).getId(),
                                client.getPhones().get(i).getPhoneNumber()));
            }
            if (updateClient.getEmails() != null) {
                IntStream.range(0, client.getEmails().size()).forEach(i ->
                        emailAddressRepository.setEmailAddressById(updateClient.getEmails().get(i).getId(),
                                client.getEmails().get(i).getEmailAddress()));
            }
            if (updateClient.getAddresses() != null) {
                IntStream.range(0, client.getAddresses().size()).forEach(i ->
                        addressRepository.setAddressById(updateClient.getAddresses().get(i).getId(),
                                client.getAddresses().get(i).getCity(),
                                client.getAddresses().get(i).getPostCode(),
                                client.getAddresses().get(i).getStreet(),
                                client.getAddresses().get(i).getBuilding(),
                                client.getAddresses().get(i).getExtension(),
                                client.getAddresses().get(i).getApartment()));
            }
            if (client.getCards() != null) {
                IntStream.range(0, client.getCards().size()).forEach(i ->
                        cardRepository.setCardById(updateClient.getCards().get(i).getId(),
                                client.getCards().get(i).getCardAssociationName(),
                                client.getCards().get(i).getCardNumber(),
                                client.getCards().get(i).getNameOnCard(),
                                client.getCards().get(i).getBillingAddress(),
                                client.getCards().get(i).getValidFrom(),
                                client.getCards().get(i).getExpiryDate(),
                                client.getCards().get(i).getCardVerificationCode(),
                                client.getCards().get(i).getDailyWithdrawalLimit(),
                                client.getCards().get(i).getBalance()));
            }
        } catch (ClientNotFoundException e) {
            log.error("Client {} not found!!! Exception: {}, Date: {}", id, e, LocalDateTime.now());
        }
    }

    private void checkPassport(Client client) throws PassportError {
        Passport passport = passportRepository.findPassportByPassportSeriesAndPassportNumber(
                client.getPassport().getPassportSeries(), client.getPassport().getPassportNumber());
        if (passport != null && !client.getId().equals(passport.getClient().getId())) {
            throw new PassportError("Attention!!! This passport belongs to another client, check client and passport!");
        }
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void saveAll(List<Client> clients) {
        clientRepository.saveAll(clients);
    }
}
