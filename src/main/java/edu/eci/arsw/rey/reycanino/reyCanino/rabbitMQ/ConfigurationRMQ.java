package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationRMQ {
	public static final String RABBITMQ_SERVER = "ec2-34-235-155-214.compute-1.amazonaws.com";
	public static final String QUEUE_NAME = "Queue ReyCaninoReserva";
	public static final int RABBITMQ_PORT = 5672;
	public static final String USERNAME = "guest";
	public static final String PASSWORD = "guest";

	@Bean
	public ConnectionRMQ createConnectionFactory() {
		return new ConnectionRMQ();
	}
	
	@Bean
	RMQInitializer rmqInitializer() {
		return new RMQInitializer();
	}
}