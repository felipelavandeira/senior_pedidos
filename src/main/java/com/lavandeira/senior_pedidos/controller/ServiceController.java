package com.lavandeira.senior_pedidos.controller;

import com.lavandeira.senior_pedidos.controller.service.OrderItemService;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.NotFoundException;
import com.lavandeira.senior_pedidos.model.Service;
import com.lavandeira.senior_pedidos.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
public class ServiceController {

    @Autowired
    private ServiceRepository repository;

    @Autowired
    private OrderItemService service;

    @GetMapping
    public ResponseEntity<Page<Service>> index(Pageable pageable){
        Page<Service> services = repository.findAll(pageable);
        return !services.isEmpty() ? ResponseEntity.ok(services) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Service> get(@PathVariable("id") long id){
        return repository.findById(id).map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Service> create(@RequestBody Service service){
        Service response = repository.save(service);
        return response.getId() != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Service> update(@PathVariable("id") long id, @RequestBody Service service){
        return repository.findById(id).map(record -> {
            record.setName(service.getName());
            record.setPrice(service.getPrice());
            record.setActive(service.getActive());
            Service response = repository.save(record);
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
