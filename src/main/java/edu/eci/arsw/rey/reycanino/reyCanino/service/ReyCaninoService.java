package edu.eci.arsw.rey.reycanino.reyCanino.service;

import edu.eci.arsw.rey.reycanino.reyCanino.DAO.HorariosDAO;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

@Service("ReyCanino")
public class ReyCaninoService {

    public Collection<Horario> consultsAvailable (Date date, String service, String petShop) throws SQLException {
        return HorariosDAO.consultAvailable(date, service, petShop);
    }
}
