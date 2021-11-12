package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.Card;
import ru.sberbank.denisov26.javacard.models.CardAssociation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying
    @Query(value = "SELECT email_address, card_number, name_on_card, expiry_date \n" +
            "FROM jpa1.email_address INNER JOIN jpa1.card\n" +
            "ON jpa1.email_address.client_id = jpa1.card.client_id\n" +
            "WHERE jpa1.card.expiry_date = :date AND jpa1.email_address.email_address IS NOT NULL", nativeQuery = true)
    List<String> findAllByExpiryDate(@Param("date") LocalDate date);

    @Modifying
    @Query(value = "SELECT * FROM jpa1.card \n" +
            "INNER JOIN jpa1.email_address \n" +
            "ON jpa1.email_address.client_id = jpa1.card.client_id \n" +
            "WHERE jpa1.card.expiry_date = :date AND jpa1.email_address.email_address IS NOT NULL", nativeQuery = true)
    List<Card> findAllCardsByExpiryDate(@Param("date")LocalDate localDate);


    @Modifying
    @Query("update Card c set c.cardAssociationName = ?2, c.cardNumber = ?3, c.nameOnCard = ?4, " +
            "c.billingAddress = ?5, c.validFrom = ?6, c.expiryDate = ?7," +
            "c.cardVerificationCode = ?8, c.dailyWithdrawalLimit = ?9," +
            "c.balance = ?10 where c.id = ?1")
    void setCardById(Long id, CardAssociation cardAssociationName, String cardNumber, String nameOnCard,
                     String billingAddress, LocalDate validFrom, LocalDate expiryDate,
                     String cardVerificationCode, Long dailyWithdrawalLimit, Long balance);

}
