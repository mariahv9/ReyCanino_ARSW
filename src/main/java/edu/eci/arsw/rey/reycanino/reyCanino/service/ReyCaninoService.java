package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("ReyCanino")
public class ReyCaninoService {

    public List<Horario> consultsAvailable (Horario horario) throws SQLException {
        return DataBaseConnection.disponibilidad(horario);
    }
    
    public String reservar(Horario horario) throws SQLException {
    	//ClienteDAO.insert(reserva.getCliente());
    	//ReservaDAO.insert(reserva);
    	return horario.getId();
    }
    
    
}