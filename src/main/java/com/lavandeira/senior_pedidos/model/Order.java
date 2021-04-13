package com.lavandeira.senior_pedidos.model;

import com.lavandeira.senior_pedidos.model.enumerated.OrderStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    @Column(name = "status")
    private OrderStatus status;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "desconto")
    private BigDecimal discount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_pedido_item",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_item")
    )
    @Fetch(value = FetchMode.JOIN)
    private List<OrderItem> items;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
