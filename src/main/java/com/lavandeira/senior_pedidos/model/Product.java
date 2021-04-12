package com.lavandeira.senior_pedidos.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "P")
public class Product extends OrderItem{
}
