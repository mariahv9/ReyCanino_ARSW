package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import com.rabbitmq.client.*;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
import edu.eci.arsw.rey.reycanino.reyCanino.utils.MailService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Receiver")
public class ReceiverRMQ {
	
	@Autowired 
	MailService mailService;
	
	@Autowired
	DataBaseConnection dbConnection;
	
    public void recibir (String message) {
    	
        String[] values = message.split("\\|");
        String[] data = values[1].split(",");
        if (values[0].equals("reservar")){
        	
        	Horario horario = new Horario();
        	Reserva reserva = new Reserva();
        	
        	horario.setId(data[0]);
        	horario.setTiendaCanina(data[6]);
        	reserva.setCliente(data[1]);
        	reserva.setCorreo(data[2]);
        	reserva.setMascota(data[3]);
        	reserva.setComentario(data[4]);
        	reserva.setTelefono(data[5]);
        	reserva.setRaza(data[7]);
        	horario.setReserva(reserva);
        	horario = dbConnection.insertarReserva(horario);
        	
        	mailService.sendValidationEmail(horario);
        	
        }else if (values[0].equals("confirmar")) {
        	
        	Reserva reserva = dbConnection.buscarReserva(data[0]);
        	Horario horario = dbConnection.buscarHorario(reserva.getIdHorario());
        	
        	if(horario.getReserva() == null) {
        		horario.setReserva(reserva);
        		
        		dbConnection.actualizarHorario(horario);
        		mailService.sendConfirmationEmail(horario);
        	}else 
        		mailService.sendErrorConfirmationEmail(reserva);
        	dbConnection.eliminarReserva(reserva);
        	
        }
    }

    public void receiveMesssage() {
        Channel channel = ConnectionRMQ.create();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                recibir(message);
            }
        };
        try {
            channel.basicConsume(ConfigurationRMQ.QUEUE_NAME, true, consumer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}