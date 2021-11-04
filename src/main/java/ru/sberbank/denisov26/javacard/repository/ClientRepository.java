package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.*;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("update Client p set p.person = ?2,  p.passport = ?3, p.phones =?4," +
            "p.addresses = ?5, p.emails = ?6, p.cards = ?7 where p.id = ?1")
    void setClientById(Long id, Person person, Passport passport, List<Phone> phones,
                       List<Address> addresses, List<EmailAddress> emails, List<Card> cards);

}
