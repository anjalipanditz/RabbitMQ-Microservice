package com.consumer.controller;

import com.consumer.service.ProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Autowired
    private ProducerClient producerClient;

    @GetMapping(path = "/select-product/{id}")
    public ResponseEntity<String> selectProduct(@PathVariable Integer id) {
        ResponseEntity<String> producerResponse = producerClient.selectProduct(id);
        return new ResponseEntity<>(producerResponse.getBody(), HttpStatus.OK);
    }
}
