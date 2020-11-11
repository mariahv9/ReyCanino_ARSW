package edu.eci.arsw.rey.reycanino.reyCanino.api;

import edu.eci.arsw.rey.reycanino.reyCanino.db.ReservaChangesListener;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.service.ReyCaninoService;
import edu.eci.arsw.rey.reycanino.reyCanino.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reyCanino")
public class APIController {
	@Autowired
	@Qualifier("ReyCanino")
	ReyCaninoService serviceR;

	@Autowired
	ReservaChangesListener reservaChangesListener;

	@Autowired
	MailService mailService;

	@RequestMapping(value = "/consultar", method = RequestMethod.POST)
	public ResponseEntity<?> consultsAvailableDates(@Valid @RequestBody Horario horario) {
		try {
			return new ResponseEntity<>(serviceR.consultsAvailable(horario), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/reservar", method = RequestMethod.POST)
	public ResponseEntity<?> reservar(@Valid @RequestBody Horario horario) {
		try {
			serviceR.reservar(horario);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/confirmar/{reserva}", method = RequestMethod.GET)
	public ModelAndView confirmacion(@PathVariable() String reserva) {
		try {
			serviceR.confirmar(reserva);
			return new ModelAndView("redirect:/correcta.html");
		} catch (Exception e) {
			Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
			return new ModelAndView("redirect:/incorrecta.html");
		}
	}

	@RequestMapping(value = "/consultar/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarReserva(@PathVariable() String id) {
		try {
			Horario horario = serviceR.consultarReserva(id);
			return new ResponseEntity<>(horario, HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/cancelar/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> cancelarReserva(@PathVariable() String id) {
		try {
			String reserva = serviceR.cancelarReserva(id);
			return new ResponseEntity<>(reserva, HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}