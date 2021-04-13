package com.lavandeira.senior_pedidos.model.repository;

import com.lavandeira.senior_pedidos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
