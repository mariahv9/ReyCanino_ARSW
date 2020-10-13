package edu.eci.arsw.rey.reycanino.reyCanino.db;

import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class DbInitializer {

	@Autowired
	private RethinkDBConnectionFactory connectionFactory;

	@Autowired
	private ReservaChangesListener reservaListener;

	private static final RethinkDB r = RethinkDB.r;

//	@Override
//	public void afterPropertiesSet() throws Exception {
////		createDb();
//		reservaListener.pushChangestoWebSocket();
//	}

	private void createDb() {
		Connection connection = connectionFactory.createConnection();
		List<String> dbList = r.dbList().run(connection);

		if (!dbList.contains("ReyCanino")) {
			r.dbCreate("ReyCanino").run(connection);
		}

		List<String> tables = r.db("ReyCanino").tableList().run(connection);
		if (!tables.contains("RESERVA")) {
			r.db("ReyCanino").tableCreate("RESERVA").run(connection);
		}
	}
}