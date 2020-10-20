package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;

@Service("Sender")
public class SenderRMQ {

	public String reservar(Horario horario) {
		String m = "";
		if (horario != null) {
			m += "reservar|";
			m += horario.getId() + ",";
			m += horario.getReserva().getCliente() + ",";
			m += horario.getReserva().getCorreo() + ",";
			m += horario.getReserva().getMascota() + ",";
			m += horario.getReserva().getComentario() + ",";
			m += horario.getReserva().getTelefono() + ",";
			m += horario.getTiendaCanina() + ",";
			m += horario.getReserva().getRaza();
			sendMessage(m);
		}
		return m;
	}

	public String confirmar(Reserva reserva) {
		String m = "";
		if (reserva != null) {
			m += "confirmar|";
			m += reserva.getId() + ",";
			m += reserva.getIdHorario();
			sendMessage(m);
		}
		return m;
	}

	private void sendMessage(String message) {
		Channel channel = ConnectionRMQ.create();
		try {
			channel.basicPublish("", ConfigurationRMQ.QUEUE_NAME, null, message.getBytes());
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
	}
}