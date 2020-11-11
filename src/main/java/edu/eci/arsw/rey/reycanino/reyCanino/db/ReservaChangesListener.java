package edu.eci.arsw.rey.reycanino.reyCanino.db;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;

@Service
public class ReservaChangesListener {

	@Autowired
	private SimpMessageSendingOperations webSocket;

	private static RethinkDB r = RethinkDB.r;

	@Async
	public void pushChangestoWebSocket() {
		Cursor<Horario> query = r.db("ReyCanino").table("HORARIO").changes().getField("new_val")
				.run(RethinkDBConnectionFactory.createConnection(), Horario.class);

		while (query.hasNext()) {
			Horario horario = query.next();
			webSocket.convertAndSend("/topic/changes", horario);
		}
	}
}