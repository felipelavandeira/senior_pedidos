package com.lavandeira.senior_pedidos.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tp_item", length = 1, discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_item_pedido")
public class OrderItem implements Serializable {

    @Id
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "item_generator", sequenceName = "tb_item_pedido_id_item_seq")
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String name;

    @DecimalMin(value = "0")
    @Column(name = "preco")
    private BigDecimal price;

    @NotNull
    @Column(name = "st_ativo")
    private Boolean active;

    @NotNull
    @Column(name = "tp_item", insertable = false, updatable = false)
    private String type;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<Order> orders;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
