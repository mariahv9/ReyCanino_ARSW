package edu.eci.arsw.rey.reycanino.reyCanino.api;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;

@Controller
public class WebSocketController {
	
    @MessageMapping("/cambios")
    @SendTo("/topic/cambios")
    public List<Horario> cambioConsulta(@DestinationVariable List<Horario> horarios) {
        System.out.println("se recibieron cambios :O");
        for (Horario h : horarios)
        	System.out.println(h);
        return null;
    }
}