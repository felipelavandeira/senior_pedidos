package com.lavandeira.senior_pedidos.model.repository;

import com.lavandeira.senior_pedidos.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
