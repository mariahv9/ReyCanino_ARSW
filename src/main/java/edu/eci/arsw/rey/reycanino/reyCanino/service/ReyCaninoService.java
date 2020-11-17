package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.ReyCaninoException;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ.SenderRMQ;
import edu.eci.arsw.rey.reycanino.reyCanino.utils.CacheService;
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

	@Autowired 
	CacheService cacheService;

	public List<Horario> consultsAvailable(Horario horario) throws SQLException {
		List<Horario> res = cacheService.getAll(horario);
		if (res == null){
			res = dbConnection.disponibilidad(horario);
			if(res.size() > 0)
				cacheService.addHorario(res);
		}
		return res;
	}

	public String reservar(Horario horario) throws SQLException {
		String reservaMessage = sender.reservar(horario);
		return reservaMessage;
	}

	public String confirmar(String id) throws SQLException, ReyCaninoException {
		Reserva reserva = dbConnection.buscarReserva(id);
		OffsetDateTime now = OffsetDateTime.now();
		if (reserva == null)
			throw new ReyCaninoException(ReyCaninoException.NO_EXISTE_RESERVA);
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