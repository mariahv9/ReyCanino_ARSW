package edu.eci.arsw.rey.reycanino.reyCanino.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RethinkDBConfiguration {
	
	@Autowired
	private Environment env;
	public static String host = "ec2-34-235-155-214.compute-1.amazonaws.com";
	public static int port = 32769;
	
	@Bean
	public RethinkDBConnectionFactory connectionFactory() {
		return new RethinkDBConnectionFactory(host, port);
	}
}