package edu.eci.arsw.rey.reycanino.reyCanino.db;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

public class PruebasDB {
	public static void main(String[] args) {
		RethinkDB r = RethinkDB.r;
		Connection connection = r.connection().hostname("ec2-34-235-155-214.compute-1.amazonaws.com").port(32769).connect();;
/*		Cursor<Horario> c =  r.db("ReyCanino").table("HORARIO")
				.filter(tienda -> tienda.getField("tiendaCanina").eq("1"))
				.filter(tienda -> tienda.getField("servicio").eq("Peluqueria"))
				.filter(tienda -> tienda.getField("reserva").eq(null))
				.filter(horario -> horario.g("fi").during(
				        r.time(2020, 10, 19, "Z"), r.time(2020, 10, 20, "Z"))
						)
				//.orderBy("fi")
                .run(connection, Horario.class);*/
		String id = "1";
		Cursor<TiendaCanina> query = r.db("ReyCanino").table("TIENDA_CANINA")
				.filter(tienda -> tienda.getField("identificacion").eq(id))
				.run(connection, TiendaCanina.class);
		for (Object o :query)
			System.out.println(o);
//		while (c.hasNext()) {
//			Horario horario = c.next();
//			System.out.println(horario);
//		}
		connection.close();
	}
}