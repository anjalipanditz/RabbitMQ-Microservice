package com.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProducerApplication {

	@Bean
	Queue queue() {
		return new Queue("test-queue",false);
	}


	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}
