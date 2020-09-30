package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.DAO.ClienteDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.DAO.HorariosDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.DAO.ReservaDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.Collection;

@Service("ReyCanino")
public class ReyCaninoService {

    public Collection<Horario> consultsAvailable (Reserva reserva) throws SQLException {
        return HorariosDAO.consultAvailable(reserva);
    }
    
    public String reservar(Reserva reserva) throws SQLException {
    	ClienteDAO.insert(reserva.getCliente());
    	ReservaDAO.insert(reserva);
    	return reserva.getIdentificador();
    }
    
}
