package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.denisov26.javacard.models.client.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
