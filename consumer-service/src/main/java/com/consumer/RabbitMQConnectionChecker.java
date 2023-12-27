package com.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnectionChecker implements CommandLineRunner {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Override
    public void run(String... args) {
        System.out.println("RabbitMQ Connection Information:");
        System.out.println("Host: " + connectionFactory.getHost());
        System.out.println("Port: " + connectionFactory.getPort());
        System.out.println("Username: " + connectionFactory.getUsername());
        //System.out.println("Password: " + connectionFactory.getPassword());
        System.out.println("Virtual Host: " + connectionFactory.getVirtualHost());
    }
}
