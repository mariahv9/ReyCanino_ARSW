package edu.eci.arsw.rey.reycanino.reyCanino.persistence;

import java.util.ArrayList;
import java.util.List;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.db.RethinkDBConnectionFactory;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCaninaPrueba;

public class DataBaseConnection {
    static RethinkDB r = RethinkDB.r;
    static Connection connection;

    public static List<TiendaCaninaPrueba> disponibilidad (Reserva reserva){
        connection = RethinkDBConnectionFactory.createConnection();
        Cursor<TiendaCaninaPrueba> c =  r.db("ReyCanino").table("TIENDA_CANINA")
        		.filter(tienda -> tienda.getField("horarios"))
                .run(connection, TiendaCaninaPrueba.class);
        List<TiendaCaninaPrueba> lista = new ArrayList<TiendaCaninaPrueba>();
        System.out.println();
        
        while (c.hasNext()) {
			TiendaCaninaPrueba tienda = c.next();
			lista.add(tienda);
		}
    
        return lista;
    }

}