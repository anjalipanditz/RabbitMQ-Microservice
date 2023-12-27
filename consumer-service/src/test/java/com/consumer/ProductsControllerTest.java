package com.consumer;

import com.consumer.controller.ProductsController;
import com.consumer.service.ProducerClient;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ProducerClient producerClient;


    @Test
    public void selectOne() throws Exception {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name","Apple IPhone 8 plus");
        ResponseEntity<String> res = new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        Mockito.when(producerClient.selectProduct(1)).thenReturn(res);
        ResponseEntity<String> response = productsController.selectProduct(1);

        assertTrue(response.getBody()!=null);
    }
}