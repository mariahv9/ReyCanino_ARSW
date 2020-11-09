package edu.eci.arsw.rey.reycanino.reyCanino.db;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class PruebasDB {
	public static void main(String[] args) {
		RethinkDB r = RethinkDB.r;
		Connection connection = r.connection().hostname("ec2-34-235-155-214.compute-1.amazonaws.com").port(32769).connect();
	}
}