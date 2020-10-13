package edu.eci.arsw.rey.reycanino.reyCanino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ReyCaninoApplication {
	public static void main (String[] args) {
		SpringApplication.run (ReyCaninoApplication.class, args);
	}
}