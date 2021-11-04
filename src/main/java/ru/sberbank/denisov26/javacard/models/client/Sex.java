package ru.sberbank.denisov26.javacard.models.client;

public enum Sex {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String displayValue;

    private Sex(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
