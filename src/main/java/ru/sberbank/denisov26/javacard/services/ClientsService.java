package ru.sberbank.denisov26.javacard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.models.client.Client;
import ru.sberbank.denisov26.javacard.repository.*;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ClientsService {
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final EmailAddressRepository emailAddressRepository;
    private final PassportRepository passportRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public ClientsService(ClientRepository clientRepository, PersonRepository personRepository, AddressRepository addressRepository, CardRepository cardRepository, EmailAddressRepository emailAddressRepository, PassportRepository passportRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.cardRepository = cardRepository;
        this.emailAddressRepository = emailAddressRepository;
        this.passportRepository = passportRepository;
        this.phoneRepository = phoneRepository;
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found!!!"));
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void update(Long id, Client client) {
        try {
            Client updateClient = findById(id);
            if (updateClient == null) return;
            personRepository.setPersonById(updateClient.getPerson().getId(), client.getPerson().getSurName(), client.getPerson().getGivenName(),
                    client.getPerson().getPatronymic(), client.getPerson().getDateOfBirth(), client.getPerson().getSex());
            passportRepository.setPassportById(updateClient.getPassport().getId(), client.getPassport().getPassportSeries(),
                    client.getPassport().getPassportNumber(), client.getPassport().getIssueDate(), client.getPassport().getIssueDepartment(),
                    client.getPassport().getCodDepartment());
            IntStream.range(0, client.getPhones().size()).forEach(i ->
                    phoneRepository.setPhoneById(updateClient.getPhones().get(i).getId(),
                            client.getPhones().get(i).getPhoneNumber()));

            IntStream.range(0, client.getEmails().size()).forEach(i ->
                    emailAddressRepository.setEmailAddressById(updateClient.getEmails().get(i).getId(),
                            client.getEmails().get(i).getEmailAddress()));

            IntStream.range(0, client.getAddresses().size()).forEach(i ->
                    addressRepository.setAddressById(updateClient.getAddresses().get(i).getId(),
                            client.getAddresses().get(i).getCity(),
                            client.getAddresses().get(i).getPostCode(),
                            client.getAddresses().get(i).getStreet(),
                            client.getAddresses().get(i).getBuilding(),
                            client.getAddresses().get(i).getExtension(),
                            client.getAddresses().get(i).getApartment()));

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
            System.err.println(e);
        }


//        try {
//            final Client clientById = findById(id);
//
//            //Person
//            clientById.getPerson().setGivenName(client.getPerson().getGivenName());
//            clientById.getPerson().setSurName((client.getPerson().getSurName()));
//            clientById.getPerson().setPatronymic(client.getPerson().getPatronymic());
//            clientById.getPerson().setDateOfBirth(client.getPerson().getDateOfBirth());
//            clientById.getPerson().setSex(client.getPerson().getSex());
//
//            //Passport
//            clientById.getPassport().setPassportSeries(client.getPassport().getPassportSeries());
//            clientById.getPassport().setPassportNumber(client.getPassport().getPassportNumber());
//            clientById.getPassport().setIssueDepartment(client.getPassport().getIssueDepartment());
//            clientById.getPassport().setIssueDate(client.getPassport().getIssueDate());
//            clientById.getPassport().setCodDepartment(client.getPassport().getCodDepartment());
//
//            //Address
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setCity(client.getAddresses().get(i).getCity()));
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setPostCode(client.getAddresses().get(i).getPostCode()));
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setStreet(client.getAddresses().get(i).getStreet()));
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setExtension(client.getAddresses().get(i).getExtension()));
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setBuilding(client.getAddresses().get(i).getBuilding()));
//            IntStream.range(0, clientById.getAddresses().size()).forEach(i -> clientById.getAddresses().get(i).setApartment(client.getAddresses().get(i).getApartment()));
//
//            //Email
//            IntStream.range(0, clientById.getEmails().size()).forEach(i -> clientById.getEmails().get(i).setEmailAddress(client.getEmails().get(i).getEmailAddress()));
//
//            //Phone
//            IntStream.range(0, clientById.getPhones().size()).forEach(i -> clientById.getPhones().get(i).setPhoneNumber(client.getPhones().get(i).getPhoneNumber()));
//
//            clientRepository.save(clientById);
//        } catch (ClientNotFoundException e) {
//            System.err.println(e);
//        }
//        clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void saveAll(List<Client> clients) {
        clientRepository.saveAll(clients);
    }
}
