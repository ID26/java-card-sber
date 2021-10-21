package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.denisov26.javacard.models.client.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
