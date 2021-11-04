package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.Phone;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Modifying
    @Query("update Phone p set p.phoneNumber = ?2 where p.id = ?1")
    void setPhoneById(Long id, String phoneNumber);
}
