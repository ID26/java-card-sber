package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.EmailAddress;
@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {
    @Modifying
    @Query("update EmailAddress e set e.emailAddress = ?2 where e.id = ?1")
    void setEmailAddressById(Long id, String emailAddress);
}
