package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.sberbank.denisov26.javacard.models.client.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
