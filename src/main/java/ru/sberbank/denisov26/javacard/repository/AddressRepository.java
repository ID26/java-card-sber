package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.denisov26.javacard.models.client.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByClientId(Long id);
}
