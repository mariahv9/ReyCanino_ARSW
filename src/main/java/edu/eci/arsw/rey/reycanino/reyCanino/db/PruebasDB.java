package edu.eci.arsw.rey.reycanino.reyCanino.db;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCaninaPrueba;

public class PruebasDB {
	
	public static void main(String[] args) {
		RethinkDB r = RethinkDB.r;
		Connection connection = r.connection().hostname("localhost").port(32769).connect();;
		
		Cursor c =  r.db("ReyCanino").table("HORARIOS")
				.filter(tienda -> tienda.getField("tiendaCanina").eq(1))
				.filter(tienda -> tienda.getField("servicio").eq("Peluqueria"))
				.filter(tienda -> tienda.getField("reserva").eq(null))
				.filter(tienda -> tienda.getField("ff").eq("2020-11-02T10:00Z"))
				//.filter(tienda -> tienda.getField("ff").eq(r.time(2020, 11, 2, 10, 0, 0, 'Z')))
				//.filter(tienda -> tienda.getField("fi").eq(r.time(2020, 11, 2, 8, 0, 0, 'Z')))
                .run(connection);

		for (Object o :c)
			System.out.println(o);
//		while (c.hasNext()) {
//			TiendaCaninaPrueba tienda = c.next();
//			System.out.println(tienda);
//		}
		
		connection.close();

	}
	

}

