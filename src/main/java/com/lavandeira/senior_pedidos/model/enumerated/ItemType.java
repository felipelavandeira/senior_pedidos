package com.lavandeira.senior_pedidos.model.enumerated;

import java.io.Serializable;

public enum ItemType implements Serializable {

    P("Produto"),
    S("Serviço");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
