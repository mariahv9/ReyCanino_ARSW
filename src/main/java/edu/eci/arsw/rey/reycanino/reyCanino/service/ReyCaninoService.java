package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.ReyCaninoException;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ.SenderRMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

@Service("ReyCanino")
public class ReyCaninoService {

	@Autowired
	SenderRMQ sender;

	@Autowired
	DataBaseConnection dbConnection;

	public List<Horario> consultsAvailable(Horario horario) throws SQLException {
		return dbConnection.disponibilidad(horario);
	}

	public String reservar(Horario horario) throws SQLException {
		String reservaMessage = sender.reservar(horario);
		return reservaMessage;
	}

	public String confirmar(String id) throws SQLException, ReyCaninoException {
		Reserva reserva = dbConnection.buscarReserva(id);
		OffsetDateTime now = OffsetDateTime.now();
    	if(now.isBefore(reserva.getFechaLimite()))
    		throw new ReyCaninoException(ReyCaninoException.TIEMPO_EXPIRADO);
		String reservaMessage = sender.confirmar(reserva);
		return reservaMessage;
	}

	public Horario consultarReserva (String id) throws SQLException {
		Horario horario = dbConnection.consultarReserva(id);
		return horario;
	}

	public String cancelarReserva (String id) throws SQLException {
		Horario horario = dbConnection.consultarReserva(id);
		if (horario != null){
			if (horario.getReserva() != null && horario.getFi().isAfter(OffsetDateTime.now())){
				dbConnection.cancelarReserva(id);
				return "Éxito";
			}
		}
		return "Fallo";
	}
}