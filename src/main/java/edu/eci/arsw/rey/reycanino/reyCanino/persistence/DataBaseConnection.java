package edu.eci.arsw.rey.reycanino.reyCanino.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.db.RethinkDBConnectionFactory;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

public class DataBaseConnection {
	static RethinkDB r = RethinkDB.r;
	static Connection connection;

	public static List<Horario> disponibilidad(Horario horarioConsulta) {
		String[] servicios = { "Peluqueria", "Paseo" };
		int a1, a2, m1, m2, d1, d2;
		Calendar c = Calendar.getInstance();
		c.setTime(horarioConsulta.getFechaConsulta());
		a1 = c.get(Calendar.YEAR);
		m1 = c.get(Calendar.MONTH) + 1;
		d1 = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DAY_OF_YEAR, 1);
		a2 = c.get(Calendar.YEAR);
		m2 = c.get(Calendar.MONTH) + 1;
		d2 = c.get(Calendar.DAY_OF_MONTH);
		connection = RethinkDBConnectionFactory.createConnection();
		ArrayList<Horario> query = r.db("ReyCanino").table("HORARIO")
				.filter(horario -> horario.getField("tiendaCanina").eq(horarioConsulta.getTiendaCanina()))
				.filter(horario -> horario.getField("servicio")
						.eq(servicios[Integer.parseInt(horarioConsulta.getServicio()) - 1]))
				.filter(horario -> horario.getField("reserva").eq(null))
				.filter(horario -> horario.g("fi").during(r.time(a1, m1, d1, "Z"), r.time(a2, m2, d2, "Z")))
				.orderBy("fi").run(connection, Horario.class);
		return query;
	}

	public static TiendaCanina buscarTiendaCanina(String id){
		TiendaCanina tiendaCanina = null;
		Cursor<TiendaCanina> query = r.db("ReyCanino").table("TIENDA_CANINA")
				.filter(tienda -> tienda.getField("identificacion").eq("\""+id+"\""))
				.run(connection, TiendaCanina.class);
		while (query.hasNext()) {
			tiendaCanina = query.next();
		}
		return tiendaCanina;
	}

	public static Horario buscarHorario(String id){
		Horario horario = null;
		Cursor<Horario> query = r.db("ReyCanino").table("HORARIO")
				.filter(hora -> hora.getField("id").eq(id))
				.run(connection, Horario.class);
		while (query.hasNext()) {
			horario = query.next();
		}
		return horario;
	}
}