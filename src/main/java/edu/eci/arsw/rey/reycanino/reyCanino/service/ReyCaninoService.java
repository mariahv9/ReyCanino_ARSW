package edu.eci.arsw.rey.reycanino.reyCanino.service;

import com.rethinkdb.net.Cursor;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ.SenderRMQ;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service("ReyCanino")
public class ReyCaninoService {

    public Cursor consultsAvailable (Reserva reserva) throws SQLException {
        return DataBaseConnection.disponibilidad(reserva);
    }
    
    public String reservar(Reserva reserva) throws SQLException { //horario entra
//    	ClienteDAO.insert(reserva.getCliente());
//    	ReservaDAO.insert(reserva);
        String reservaMessage = SenderRMQ.messageSend(reserva.getIdentificador());
    	return reservaMessage;
    }
}