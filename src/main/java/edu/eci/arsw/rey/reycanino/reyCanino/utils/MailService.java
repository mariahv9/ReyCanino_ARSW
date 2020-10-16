package edu.eci.arsw.rey.reycanino.reyCanino.utils;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Calendar;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

@Service("Mail")
public class MailService {


    //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String code, Reserva reserva) {
    	MimeMessagePreparator preparator = null;
    	/*
    	try {
    		final TiendaCanina  shop = TiendaCaninaDAO.findById(reserva.getHorario().getPetshop());;
        	final Servicio service = ServicioDAO.findById(reserva.getHorario().getService());
        	final Horario horario = HorariosDAO.findById(reserva.getHorario().getId());
        	
			preparator = new MimeMessagePreparator() {
		           public void prepare(MimeMessage mimeMessage) throws Exception {
		              MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		              message.setTo(reserva.getCliente().getCorreo());
		              message.setFrom("reycaninostore@gmail.com");
		              message.setSubject("¡Reserva exitosa!");;
		              

		              VelocityContext velocityContext = new VelocityContext();
		              velocityContext.put("user", reserva.getCliente().getNombreMascota());
		              velocityContext.put("codigo", code); 
		              velocityContext.put("nombreTienda", shop.getNombre()); 
		              velocityContext.put("direccion", shop.getDireccion()); 
		              velocityContext.put("nombreServicio", service.getNombre());
		              String dateAux = "";
		              Calendar calendar = Calendar.getInstance();
		              calendar.setTime(reserva.getFecha());
		              dateAux += calendar.get(Calendar.YEAR) + "-";
		              dateAux += (calendar.get(Calendar.MONTH)+1 > 9)?"":"0";
		              dateAux += calendar.get(Calendar.MONTH)+1 + "-";
		              dateAux += (calendar.get(Calendar.DAY_OF_MONTH) > 9)?"":"0";
		              dateAux += calendar.get(Calendar.DAY_OF_MONTH);
		              dateAux += " - " + horario.getTimeStart().toString(); 
		              velocityContext.put("fecha", dateAux); 
		              
		              StringWriter text = new StringWriter();
		              VelocityEngine ve = new VelocityEngine();
		              ve.mergeTemplate("plantilla.vm", "UTF-8", velocityContext, text);
		              message.setText(text.toString(), true);
		           }

		        };
		} catch (SQLException e) {
			e.printStackTrace();
		}

        this.mailSender.send(preparator);
        */
     }
}