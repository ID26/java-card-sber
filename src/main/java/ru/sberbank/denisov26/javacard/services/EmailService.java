package ru.sberbank.denisov26.javacard.services;

public interface EmailService {
    void send(String to, String subject, String text );
}
