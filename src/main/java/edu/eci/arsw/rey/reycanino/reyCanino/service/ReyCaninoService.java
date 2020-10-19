package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ.ReceiverRMQ;
import edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ.SenderRMQ;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service("ReyCanino")
public class ReyCaninoService {
    public List<Horario> consultsAvailable (Horario horario) throws SQLException {
        return DataBaseConnection.disponibilidad(horario);
    }
    
    public String reservar(Horario horario) throws SQLException {
        String reservaMessage = SenderRMQ.reservar(horario);
        ReceiverRMQ.receiveMesssage();
        return reservaMessage;
    }

    public String confirmar(Horario horario) throws SQLException {
        String reservaMessage = SenderRMQ.confirmar(horario);
        ReceiverRMQ.receiveMesssage();
        return reservaMessage;
    }
}