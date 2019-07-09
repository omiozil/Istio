package com.orchestOrder.orderOrchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OrderOrchestrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOrchestrationApplication.class, args);
	}

}
