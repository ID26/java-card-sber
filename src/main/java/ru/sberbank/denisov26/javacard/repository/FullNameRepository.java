package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.denisov26.javacard.models.client.Person;

import java.util.List;

public interface FullNameRepository extends JpaRepository<Person, Long> {
    List<Person> findBySurName(String surname);
}
