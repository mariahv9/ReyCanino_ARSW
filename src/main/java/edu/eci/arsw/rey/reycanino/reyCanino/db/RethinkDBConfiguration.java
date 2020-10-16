package edu.eci.arsw.rey.reycanino.reyCanino.db;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RethinkDBConfiguration {
	
	@Autowired
	private Environment env;
	//public static String host = "ec2-34-235-155-214.compute-1.amazonaws.com";
	public static String host = "localhost";
	public static int port = 32769;
	
//	@PostConstruct
//	public void init() {
//		RethinkDBConfiguration.host = this.env.getProperty("rethinkdb.host");
//	}
	
	@Bean
	public RethinkDBConnectionFactory connectionFactory() {
		return new RethinkDBConnectionFactory(host, port);
	}
}