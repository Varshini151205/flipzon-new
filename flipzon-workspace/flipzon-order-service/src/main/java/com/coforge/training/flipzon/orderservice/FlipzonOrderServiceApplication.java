package com.coforge.training.flipzon.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class FlipzonOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipzonOrderServiceApplication.class, args);
	}

}
