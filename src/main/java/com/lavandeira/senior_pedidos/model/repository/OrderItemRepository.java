package com.lavandeira.senior_pedidos.model.repository;

import com.lavandeira.senior_pedidos.model.Order;
import com.lavandeira.senior_pedidos.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
