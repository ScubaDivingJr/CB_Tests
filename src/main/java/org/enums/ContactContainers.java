package org.enums;

public enum ContactContainers {
    ADDRESS(0),
    PHONE (1),
    EMAIL(2);
    final int value;

    ContactContainers(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
