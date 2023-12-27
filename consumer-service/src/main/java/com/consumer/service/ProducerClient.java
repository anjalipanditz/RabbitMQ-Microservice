package com.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producer-service", url = "http://localhost:2222", path = "/producer-service")
public interface ProducerClient {

    @GetMapping("/select-product/{id}")
    public ResponseEntity<String> selectProduct(@PathVariable("id") int id);

}
