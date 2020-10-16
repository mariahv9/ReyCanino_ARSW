package edu.eci.arsw.rey.reycanino.reyCanino.db;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;

@Service
public class ReservaChangesListener {
	
	@Autowired
	private RethinkDBConnectionFactory connectionFactory;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	private static RethinkDB r = RethinkDB.r; 
	
	@Async
	public void pushChangestoWebSocket(Horario horarioConsulta) {
		String[] servicios = {"Peluqueria", "Paseo"};
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
        
    	System.out.println(horarioConsulta.getTiendaCanina());
    	System.out.println(servicios[Integer.parseInt(horarioConsulta.getServicio()) - 1]);
    	System.out.println(a1+" " +m1+" " + d1+" hasta " +a2+" " + m2+" " + d2);
		Cursor<Horario> query =  r.db("ReyCanino").table("HORARIO")
				.changes()
				.filter(horario -> horario.getField("tiendaCanina").eq(horarioConsulta.getTiendaCanina()))
				.filter(horario -> horario.getField("servicio").eq(servicios[Integer.parseInt(horarioConsulta.getServicio()) - 1]))
				.filter(horario -> horario.getField("reserva").eq(null))
				.filter(horario -> horario.g("fi").during(
						r.time(a1, m1, d1, "Z"), r.time(a2, m2, d2, "Z"))
						)
				.run(connectionFactory.createConnection(), Horario.class);
		
		List<Horario> lista = new ArrayList<Horario>();
		
		while (query.hasNext()) {
        	Horario tienda = query.next();
			lista.add(tienda);
			System.out.println(tienda);
		}
		
		webSocket.convertAndSend("/topic/reserva",lista);
		
	}
}