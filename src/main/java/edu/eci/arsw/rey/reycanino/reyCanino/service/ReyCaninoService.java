package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.DAO.ClienteDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.DAO.ReservaDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCaninaPrueba;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("ReyCanino")
public class ReyCaninoService {

    public List<TiendaCaninaPrueba> consultsAvailable (Reserva reserva) throws SQLException {
        return DataBaseConnection.disponibilidad(reserva);
    }
    
    public String reservar(Reserva reserva) throws SQLException {
    	ClienteDAO.insert(reserva.getCliente());
    	ReservaDAO.insert(reserva);
    	return reserva.getIdentificador();
    }
    
    
}