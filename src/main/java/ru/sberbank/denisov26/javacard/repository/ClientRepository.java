package ru.sberbank.denisov26.javacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.models.client.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
