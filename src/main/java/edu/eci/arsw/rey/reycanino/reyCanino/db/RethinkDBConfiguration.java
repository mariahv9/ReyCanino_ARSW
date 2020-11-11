package edu.eci.arsw.rey.reycanino.reyCanino.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class RethinkDBConfiguration {

	public static String host = "ec2-34-235-155-214.compute-1.amazonaws.com";
	public static int port = 32769;

	@Bean
	ChangesInitializer dbInitializer() {
		return new ChangesInitializer();
	}

	@Bean
	public RethinkDBConnectionFactory connectionFactory() {
		return new RethinkDBConnectionFactory(host, port);
	}
}