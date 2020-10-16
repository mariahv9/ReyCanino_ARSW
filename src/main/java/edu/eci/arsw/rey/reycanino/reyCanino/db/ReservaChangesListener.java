package edu.eci.arsw.rey.reycanino.reyCanino.db;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;

@Service
public class ReservaChangesListener {
	
	@Autowired
	private RethinkDBConnectionFactory connectionFactory;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	private static RethinkDB r = RethinkDB.r; 
	
	@Async
	public Cursor pushChangestoWebSocket(Reserva reserva) {
//		Cursor<Reserva> cursor =  r.db("ReyCanino").table("RESERVA").changes()
//				.getField("new_reserva")
//				.run(connectionFactory.createConnection(), Reserva.class);
		Cursor c = r.db("ReyCanino").table("HORARIO")
				.filter(servicio -> servicio.getField("id_servicio").eq("1"))
				.filter(servicio -> servicio.getField("id_tienda_canina").eq("1"))
				.filter(servicio -> servicio.getField("dia").eq("5"))
				.outerJoin((r.db("ReyCanino").table("RESERVA")
								.eqJoin("horario", r.db("ReyCanino").table("HORARIO"))
								.zip()
								.filter(horario -> horario.getField("id_servicio").eq("1"))
								.filter(horario -> horario.getField("id_tienda_canina").eq("1"))
								.filter(horario -> horario.getField("fecha").eq(r.time(2020, 9, 26, "Z")))
								.pluck("horario")),
						(horario_row,reserva_row)->
								horario_row.g("id")
										.eq(reserva_row.g("horario"))
										.or(reserva_row.hasFields("horario").not())
				)
				.zip()
				.filter(horario -> horario.hasFields("horario").not())
				.changes()
				.run(connectionFactory.createConnection());
		
		for (Object o : c) {
			System.out.println(o);			
		}
		while(c.hasNext()) {
			Object horario = c.next();
			Log.info("new horario:{}", horario);
//			webSocket.convertAndSend("/topic/reserva",reserva);
		}
		return c;
	}
}