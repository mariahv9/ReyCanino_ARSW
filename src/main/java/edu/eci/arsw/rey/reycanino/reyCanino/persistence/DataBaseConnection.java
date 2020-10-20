package edu.eci.arsw.rey.reycanino.reyCanino.persistence;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.db.RethinkDBConnectionFactory;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

@Service("DbConnection")
public class DataBaseConnection {

	static RethinkDB r = RethinkDB.r;
	static Connection connection;

	public List<Horario> disponibilidad(Horario horarioConsulta) {
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

	public TiendaCanina buscarTiendaCanina(String id) {

		connection = RethinkDBConnectionFactory.createConnection();
		Cursor<TiendaCanina> query = r.db("ReyCanino").table("TIENDA_CANINA")
				.filter(tienda -> tienda.getField("identificacion").eq(id)).run(connection, TiendaCanina.class);

		TiendaCanina tiendaCanina = null;
		while (query.hasNext()) {
			tiendaCanina = query.next();
		}
		return tiendaCanina;
	}

	public Horario buscarHorario(String id) {
		Horario horario = null;
		connection = RethinkDBConnectionFactory.createConnection();
		Cursor<Horario> query = r.db("ReyCanino").table("HORARIO").filter(hora -> hora.getField("id").eq(id))
				.run(connection, Horario.class);
		while (query.hasNext()) {
			horario = query.next();
		}
		return horario;
	}

	public Reserva buscarReserva(String id) {
		Reserva reserva = null;
		connection = RethinkDBConnectionFactory.createConnection();
		Cursor<Reserva> query = r.db("ReyCanino").table("RESERVA").filter(res -> res.getField("id").eq(id))
				.run(connection, Reserva.class);
		while (query.hasNext()) {
			reserva = query.next();
		}
		return reserva;
	}

	public void actualizarHorario(Horario horario) {

		connection = RethinkDBConnectionFactory.createConnection();
		r.db("ReyCanino").table("HORARIO").get(horario.getId()).update(r.hashMap("reserva",
				r.hashMap("cliente", horario.getReserva().getCliente()).with("correo", horario.getReserva().getCorreo())
						.with("mascota", horario.getReserva().getMascota())
						.with("comentario", horario.getReserva().getComentario())
						.with("telefono", horario.getReserva().getTelefono())
						.with("raza", horario.getReserva().getRaza())))
				.run(connection);
	}

	public Horario insertarReserva(Horario horario) {
		OffsetDateTime nowDateTime = OffsetDateTime.now();
		nowDateTime.plusDays(1);
		connection = RethinkDBConnectionFactory.createConnection();

		HashMap<String, Object> insert = r.db("ReyCanino").table("RESERVA")
				.insert(r.array(r.hashMap("fechaLimite", nowDateTime).with("cliente", horario.getReserva().getCliente())
						.with("correo", horario.getReserva().getCorreo())
						.with("mascota", horario.getReserva().getMascota())
						.with("comentario", horario.getReserva().getComentario())
						.with("telefono", horario.getReserva().getTelefono())
						.with("raza", horario.getReserva().getRaza()).with("idHorario", horario.getId())))
				.run(connection);

		ArrayList<String> llaves = (ArrayList<String>) insert.get("generated_keys");

		horario.getReserva().setId(llaves.get(0));

		return horario;
	}

	public void eliminarReserva(Reserva reserva) {
		connection = RethinkDBConnectionFactory.createConnection();

		r.db("ReyCanino").table("RESERVA").get(reserva.getId()).delete().run(connection);

	}

}