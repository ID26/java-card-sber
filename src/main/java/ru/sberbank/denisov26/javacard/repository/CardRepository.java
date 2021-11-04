package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.models.client.CardAssociation;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByExpiryDate(LocalDate date);

    @Modifying
    @Query("update Card c set c.cardAssociationName = ?2, c.cardNumber = ?3, c.nameOnCard = ?4, " +
            "c.billingAddress = ?5, c.validFrom = ?6, c.expiryDate = ?7," +
            "c.cardVerificationCode = ?8, c.dailyWithdrawalLimit = ?9," +
            "c.balance = ?10 where c.id = ?1")
    void setCardById(Long id, CardAssociation cardAssociationName, String cardNumber, String nameOnCard,
                     String billingAddress, LocalDate validFrom, LocalDate expiryDate,
                     String cardVerificationCode, String dailyWithdrawalLimit, String balance);


}
