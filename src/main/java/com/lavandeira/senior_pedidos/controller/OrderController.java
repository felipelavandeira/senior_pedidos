package com.lavandeira.senior_pedidos.controller;

import com.lavandeira.senior_pedidos.controller.service.OrderService;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.NotFoundException;
import com.lavandeira.senior_pedidos.model.Order;
import com.lavandeira.senior_pedidos.model.OrderItem;
import com.lavandeira.senior_pedidos.model.repository.OrderItemRepository;
import com.lavandeira.senior_pedidos.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<Page<Order>> index(Pageable pageable) {
        Page<Order> orders = repository.findAll(pageable);
        return !orders.isEmpty() ? ResponseEntity.ok(orders) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        order.setItems(service.getItems(order));
        order.setTotal(BigDecimal.ZERO);
        service.discountIsApplicable(order);
        Order response = repository.save(service.calculateTotal(order));
        return response.getId() != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable("id") long id, @RequestBody Order order) {
        return repository.findById(id).map(record -> {
            if (!record.getDiscount().equals(order.getDiscount())){
                record.setDiscount(order.getDiscount());
                service.discountIsApplicable(record);
            }
            record.setItems(service.getItems(order));
            record.setStatus(order.getStatus());
            Order response = repository.save(service.calculateTotal(record));
            return ResponseEntity.ok(response);
        }).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(NotFoundException::new);
    }

}
