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
	
	public static String DBHOST = "localhost";
	
	@PostConstruct
	public void init() {
		RethinkDBConfiguration.DBHOST = this.env.getProperty("rethinkdb.dbhost");
	}
	
	@Bean
	public RethinkDBConnectionFactory connectionFactory() {
		return new RethinkDBConnectionFactory(DBHOST);
	}
	
	@Bean
	DbInitializer dbInitializer() {
		return new DbInitializer();
	}

}
