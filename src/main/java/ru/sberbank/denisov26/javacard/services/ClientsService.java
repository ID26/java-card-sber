package ru.sberbank.denisov26.javacard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.denisov26.javacard.models.client.Client;
import ru.sberbank.denisov26.javacard.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }


    public void update(Long id, Client client) {
//        System.out.println(id);
        System.out.println(client.getPerson().getId());
//        System.out.println(client.getPerson().getId);
//        System.out.println(client.getPerson().getId);
//        System.out.println(client.getPerson().getId);
//        System.out.println(id);
        client.setId(id);
//        client.get
//        clientRepository.deleteById(id);
        clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
