package ru.sberbank.denisov26.javacard.exceptions;

public class PassportError extends Exception {
    private final String message;
    public PassportError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
