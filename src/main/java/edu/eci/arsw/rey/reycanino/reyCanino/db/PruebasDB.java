package edu.eci.arsw.rey.reycanino.reyCanino.db;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.jsp.jstl.sql.Result;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

public class PruebasDB {
	public static void main(String[] args) {
		RethinkDB r = RethinkDB.r;
//		Connection connection = r.connection().hostname("ec2-34-235-155-214.compute-1.amazonaws.com").port(32769).connect();;
		Connection connection = r.connection().hostname("localhost").port(32769).connect();

		HashMap<String, Integer> resp = r.db("ReyCanino").table("HORARIO").get("241f131f-cdd7-45a9-8b9d-1e00f7418bb0")
				.update(r.hashMap("reserva", r.hashMap("cliente", "sebas")
						.with("correo", "sebas@gmail.com")
						.with("mascota","dantez")
						.with("comentario","pulgas")
						.with("telefono","1234567")
						.with("raza","pitbull")
						))

				// .orderBy("fi")
				.run(connection);
		
//		HashMap<String, Integer> c = r.db("ReyCanino").table("HORARIO").get("241f131f-cdd7-45a9-8b9d-1e00f7418bb0")
//				.update(r.hashMap("horario",null))
//				.run(connection);
//		
		
		
		Cursor<Horario> c = r.db("ReyCanino").table("HORARIO").filter(res -> res.getField("id").eq("241f131f-cdd7-45a9-8b9d-1e00f7418bb0"))
				.run(connection,Horario.class);
		
		for (Horario o :c) {
			System.out.println(o);
			System.out.println(o.getReserva() == null);
		}
//		while (c.hasNext()) {
//			Reserva horario = c.next();
//			System.out.println(horario);
//		}
		connection.close();
	}
}