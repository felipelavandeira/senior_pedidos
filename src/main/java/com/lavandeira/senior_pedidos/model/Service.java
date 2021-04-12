package com.lavandeira.senior_pedidos.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "S")
public class Service extends OrderItem{
}
