package com.producer;

import com.producer.controller.ProductsController;
import com.producer.entity.Product;
import com.producer.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Mock
    ProductRepository productRepository;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Test
    public void saveProduct() throws Exception {

        Product product = new Product("Apple IPhone 8 plus","Apple",100000.0,"Apple IPhone 8 plus");

        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity<String> response = productsController.addProducts(product);

        assertTrue(response!=null);
        assertEquals("Product added",response.getBody());
    }

    @Test
    public void findAllProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product("Apple IPhone 8 plus","Apple",100000.0,"Apple IPhone 8 plus");
        products.add(product);
        Mockito.when(productRepository.findAll()).thenReturn((Iterable<Product>)products);

        ResponseEntity<Iterable<Product>> response = productsController.displayAll();

        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    public void selectOne() throws Exception {
        Product product = new Product("Apple IPhone 8 plus","Apple",100000.0,"Apple IPhone 8 plus");
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ResponseEntity<String> response = productsController.selectProduct(1);

        assertTrue(response.getBody()!=null);
    }
}