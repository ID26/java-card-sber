package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.EmailAddress;

import java.util.List;

@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {
    @Modifying
    @Query("update EmailAddress e set e.emailAddress = ?2 where e.id = ?1")
    void setEmailAddressById(Long id, String emailAddress);

    @Modifying
    @Query(value = "SELECT * FROM jpa1.email_address \n" +
            "WHERE jpa1.email_address.client_id = :id AND jpa1.email_address.email_address IS NOT NULL", nativeQuery = true)
    List<EmailAddress> findAllByClientId(@Param("id")Long id);
}
