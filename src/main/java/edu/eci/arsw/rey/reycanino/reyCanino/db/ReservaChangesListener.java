package edu.eci.arsw.rey.reycanino.reyCanino.db;

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
	public void pushChangestoWebSocket() {
		Cursor<Reserva> cursor =  r.db("reycanino").table("reserva").changes()
				.getField("new_reserva")
				.run(connectionFactory.createConnection(), Reserva.class);
		
		while(cursor.hasNext()) {
			Reserva reserva = cursor.next();
			Log.info("new reserva:{}", Reserva.class);
			webSocket.convertAndSend("/topic/reserva",reserva);
		}
	}

}
