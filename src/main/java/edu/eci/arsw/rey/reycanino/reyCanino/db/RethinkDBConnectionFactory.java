package edu.eci.arsw.rey.reycanino.reyCanino.db;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class RethinkDBConnectionFactory {
	private static String host;
	private static int port;

	public RethinkDBConnectionFactory(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static Connection createConnection(){
		System.out.println(host + " " + port);
		return RethinkDB.r.connection().hostname(host).port(port).connect();
	}
}