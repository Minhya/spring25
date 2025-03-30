package org.example.spring25;

import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring25Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring25Application.class, args);
	}




}
