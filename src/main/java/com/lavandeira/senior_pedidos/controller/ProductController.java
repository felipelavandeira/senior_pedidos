package com.lavandeira.senior_pedidos.controller;

import com.lavandeira.senior_pedidos.controller.service.OrderItemService;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.NotFoundException;
import com.lavandeira.senior_pedidos.model.Product;
import com.lavandeira.senior_pedidos.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private OrderItemService service;

    @GetMapping
    public ResponseEntity<Page<Product>> index(Pageable pageable){
        Page<Product> products = repository.findAll(pageable);
        return !products.isEmpty() ? ResponseEntity.ok(products) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> get(@PathVariable("id") long id){
        return repository.findById(id).map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product response = repository.save(product);
        return response.getId() != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody Product product){
        return repository.findById(id).map(record -> {
            record.setName(product.getName());
            record.setPrice(product.getPrice());
            record.setActive(product.getActive());
            Product response = repository.save(record);
            return ResponseEntity.ok(response);
        }).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id).map(record -> {
            service.itemIsDeletable(record);
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(NotFoundException::new);
    }

}
