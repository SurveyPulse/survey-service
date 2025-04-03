package com.example.servey_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.servey_service", "com.example.global"})
@EnableJpaAuditing
public class ServeyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeyServiceApplication.class, args);
	}

}
