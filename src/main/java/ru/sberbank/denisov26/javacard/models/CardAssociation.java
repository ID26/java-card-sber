package ru.sberbank.denisov26.javacard.models;

public enum CardAssociation {
    VISA("VISA"),
    MASTER_CARD("MasterCard"),
    MIR("МИР");

    private final String displayValue;

    private CardAssociation(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
