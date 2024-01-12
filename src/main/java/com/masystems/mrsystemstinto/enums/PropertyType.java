package com.masystems.mrsystemstinto.enums;

public enum PropertyType {
    TYPE(0), COLOR(1), DEFECT(2), TEXTURE(3);

    private final int value;

    private PropertyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
