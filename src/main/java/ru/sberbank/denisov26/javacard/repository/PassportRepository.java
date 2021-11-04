package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.Passport;

import java.time.LocalDate;
@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    @Modifying
    @Query("update Passport p set p.passportSeries = ?2,  p.passportNumber = ?3, p.issueDate =?4," +
            "p.issueDepartment = ?5, p.codDepartment = ?6 where p.id = ?1")
    void setPassportById(Long id, String passportSeries, String passportNumber, LocalDate issueDate,
                         String issueDepartment, String codDepartment);
}
