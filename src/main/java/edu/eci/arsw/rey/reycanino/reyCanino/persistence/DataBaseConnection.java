package edu.eci.arsw.rey.reycanino.reyCanino.persistence;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.db.RethinkDBConnectionFactory;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;

public class DataBaseConnection {
    static RethinkDB r = RethinkDB.r;
    static Connection connection;

    public static Cursor disponibilidad (Reserva reserva){
        connection = RethinkDBConnectionFactory.createConnection();
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
                .run(connection);
        for (Object o: c){
            System.out.println(o);
        }
        return c;
    }


}