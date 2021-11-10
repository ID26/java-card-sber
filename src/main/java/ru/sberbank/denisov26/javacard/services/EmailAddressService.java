package ru.sberbank.denisov26.javacard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.denisov26.javacard.models.client.EmailAddress;
import ru.sberbank.denisov26.javacard.repository.EmailAddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailAddressService {

    private final EmailAddressRepository emailAddressRepository;

    public List<EmailAddress> findAllByClientId(Long id) {
        return emailAddressRepository.findAllByClientId(id);
    }
}
