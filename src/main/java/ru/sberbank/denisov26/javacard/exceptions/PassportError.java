package ru.sberbank.denisov26.javacard.exceptions;

public class PassportError extends Exception {
    private String messade;
    public PassportError(String message) {
        super(message);
        this.messade = message;
    }

    @Override
    public String toString() {
        return messade;
    }
}
