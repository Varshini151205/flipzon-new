package com.coforge.training.flipzon.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class FlipzonAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipzonAdminServiceApplication.class, args);
	}

}
