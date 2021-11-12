package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import ru.sberbank.denisov26.javacard.models.Address;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByClientId(Long id);

    @Modifying
    @Query("update Address a set a.city = ?2, a.postCode = ?3, a.street = ?4, " +
            "a.building = ?5, a.extension = ?6, a.apartment = ?7   where a.id = ?1")
    void setAddressById(Long id, String city, String postCode, String street,
                        String building, String extension, String apartment);
}
