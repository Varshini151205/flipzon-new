package com.coforge.training.flipzon.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlipzonDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipzonDiscoveryServerApplication.class, args);
	}

}