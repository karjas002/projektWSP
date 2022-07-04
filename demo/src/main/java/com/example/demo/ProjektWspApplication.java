package com.example.demo;

import com.example.demo.repository.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackageClasses = OrderRepository.class)
//@EntityScan(basePackages = {"com.example.demo.model.entities"})
public class ProjektWspApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektWspApplication.class, args);
	}

}
