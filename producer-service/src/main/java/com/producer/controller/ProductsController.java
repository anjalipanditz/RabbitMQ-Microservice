package com.producer.controller;

import com.producer.entity.Product;
import com.producer.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Queue;

@RestController
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addProducts(@RequestBody Product product) {
        Product res = productRepository.save(product);
        if (res != null) {
            return new ResponseEntity<>("Product added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not added", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/display-all")
    public ResponseEntity<Iterable<Product>> displayAll() {
        Iterable<Product> list = productRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/select-product/{id}")
    public ResponseEntity<String> selectProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        rabbitTemplate.convertAndSend("test-queue", product.get().toString());
        return new ResponseEntity<>(product.get().toString(), HttpStatus.OK);
    }
}
