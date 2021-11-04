package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.Person;
import ru.sberbank.denisov26.javacard.models.client.Sex;

import java.time.LocalDate;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Modifying
    @Query("update Person p set p.surName = ?2,  p.givenName = ?3, p.patronymic = ?4," +
            " p.dateOfBirth =?5, p.sex = ?6 where p.id = ?1")
    void setPersonById(Long id, String serName, String givenName, String patronymic,
                       LocalDate dateOfBirth, Sex sex);
}
