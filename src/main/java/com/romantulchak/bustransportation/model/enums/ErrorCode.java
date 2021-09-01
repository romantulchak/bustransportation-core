package com.romantulchak.bustransportation.model.enums;

public enum ErrorCode {
    USER_DISABLED(1),
    USER_DATA_INCORRECT(2),
    USER_TOKEN_NOT_FOUND(3),
    USER_TOKEN_EXPIRED(4);

    private int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
