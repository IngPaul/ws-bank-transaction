package com.pichincha.core.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pichincha.core.model.Transaction;

public enum TransactionTypedEnum {
    DEPOSITO(1l),

    RETIRO(2l),

    TRANSFERENCIA(3l);

    private Long value;

    TransactionTypedEnum(Long value) {
        this.value = value;
    }

    @JsonValue
    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static TransactionTypedEnum fromValue(Long value) {
        for (TransactionTypedEnum b : TransactionTypedEnum.values()) {
            if (b.getValue().equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}