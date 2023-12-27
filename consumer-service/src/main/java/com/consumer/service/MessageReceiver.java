package com.consumer.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // Process the message as needed
    }
}
