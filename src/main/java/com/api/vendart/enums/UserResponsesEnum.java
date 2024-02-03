package com.api.vendart.enums;

public enum UserResponsesEnum {
    SIM(1), NAO(0);
    private int value;
    UserResponsesEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
