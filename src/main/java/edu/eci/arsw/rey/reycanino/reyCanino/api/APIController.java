package edu.eci.arsw.rey.reycanino.reyCanino.api;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.service.ReyCaninoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping (value = "/reyCanino")
public class APIController {
    @Autowired
    @Qualifier("ReyCanino")
    ReyCaninoService serviceR;

    @RequestMapping(value = "/{date}/{service}/{petshop}", method = RequestMethod.GET)
    public ResponseEntity<?> consultsAvailableDates (@PathVariable("date") Date date, @PathVariable("service") String service, @PathVariable("petshop") String petShop){
        try {
            return new ResponseEntity<>(serviceR.consultsAvailable(date, service, petShop), HttpStatus.ACCEPTED);
        }catch (Exception e){
            Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/reservar", method = RequestMethod.POST)
    public ResponseEntity<?> reservar (@Valid @RequestBody Reserva reserva){
    	try {
    		String codigo = serviceR.reservar(reserva);
    		return new ResponseEntity<>(codigo, HttpStatus.OK);
    	}catch (Exception e){
    		Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, e);
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}