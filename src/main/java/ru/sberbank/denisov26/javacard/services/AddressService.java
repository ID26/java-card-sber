package ru.sberbank.denisov26.javacard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.denisov26.javacard.models.client.Address;
import ru.sberbank.denisov26.javacard.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAllByClientId(Long id) {
        return addressRepository.findAllByClientId(id);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public void update(/*Long id, */Address address) {
        addressRepository.save(address);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
