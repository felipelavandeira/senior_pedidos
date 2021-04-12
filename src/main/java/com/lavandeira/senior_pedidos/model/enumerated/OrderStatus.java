package com.lavandeira.senior_pedidos.model.enumerated;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

    OPENED("Aberto"),
    CLOSED("Fechado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
