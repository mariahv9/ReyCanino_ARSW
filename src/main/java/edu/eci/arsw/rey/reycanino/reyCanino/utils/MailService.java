package edu.eci.arsw.rey.reycanino.reyCanino.utils;

import java.io.StringWriter;
import javax.mail.internet.MimeMessage;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;
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

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	DataBaseConnection dbConnection;

	public void sendValidationEmail(Horario horario) {
		MimeMessagePreparator preparator = null;

		try {

			final TiendaCanina shop = dbConnection.buscarTiendaCanina(horario.getTiendaCanina());
			final Horario horarioDB = dbConnection.buscarHorario(horario.getId());

			preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(horario.getReserva().getCorreo());
					message.setFrom("reycaninostore@gmail.com");
					message.setSubject("¡Confirma tu reserva!");

					String url = "http://localhost:8080/reyCanino/confirmar/";
//					String url = "http://reycanino.herokuapp.com/reyCanino/confirmar/";
					url += horario.getReserva().getId();

					String dateAux = "";
					int dia = horarioDB.getFi().getDayOfMonth();
					int mes = horarioDB.getFi().getMonth().getValue();
					int anio = horarioDB.getFi().getYear();
					int hora = horarioDB.getFi().getHour();
					int minuto = horarioDB.getFi().getMinute();
					String min = (minuto > 9) ? Integer.toString(minuto) : "0" + minuto;

					dateAux = dia + "/" + mes + "/" + anio + " - " + hora + ":" + min;

					VelocityContext velocityContext = new VelocityContext();
					velocityContext.put("user", horario.getReserva().getCliente());
					velocityContext.put("nombreMascota", horario.getReserva().getMascota());
					velocityContext.put("nombreTienda", shop.getNombre());
					velocityContext.put("direccion", shop.getDireccion());
					velocityContext.put("nombreServicio", horarioDB.getServicio());
					velocityContext.put("pagina", url);
					velocityContext.put("fecha", dateAux);

					StringWriter text = new StringWriter();
					VelocityEngine ve = new VelocityEngine();
					ve.mergeTemplate("validacion.vm", "UTF-8", velocityContext, text);
					message.setText(text.toString(), true);
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(preparator);
	}

	public void sendConfirmationEmail(Horario horario) {
		MimeMessagePreparator preparator = null;
		try {

			final TiendaCanina shop = dbConnection.buscarTiendaCanina(horario.getTiendaCanina());

			preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(horario.getReserva().getCorreo());
					message.setFrom("reycaninostore@gmail.com");
					message.setSubject("¡Resumen de tu reserva!");

					String dateAux = "";
					int dia = horario.getFi().getDayOfMonth();
					int mes = horario.getFi().getMonth().getValue();
					int anio = horario.getFi().getYear();
					int hora = horario.getFi().getHour();
					int minuto = horario.getFi().getMinute();

					dateAux = dia + "/" + mes + "/" + anio + " - " + hora + ":" + minuto;

					VelocityContext velocityContext = new VelocityContext();
					velocityContext.put("user", horario.getReserva().getCliente());
					velocityContext.put("nombreMascota", horario.getReserva().getMascota());
					velocityContext.put("codigo", horario.getId());
					velocityContext.put("nombreTienda", shop.getNombre());
					velocityContext.put("direccion", shop.getDireccion());
					velocityContext.put("nombreServicio", horario.getServicio());
					velocityContext.put("fecha", dateAux);

					StringWriter text = new StringWriter();
					VelocityEngine ve = new VelocityEngine();
					ve.mergeTemplate("plantilla.vm", "UTF-8", velocityContext, text);
					message.setText(text.toString(), true);
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.mailSender.send(preparator);
	}

	public void sendErrorConfirmationEmail(Reserva reserva) {
		MimeMessagePreparator preparator = null;
		try {
			preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(reserva.getCorreo());
					message.setFrom("reycaninostore@gmail.com");
					message.setSubject("¡Ops ha ocurrido un error!");

					VelocityContext velocityContext = new VelocityContext();
					velocityContext.put("user", reserva.getCliente());

					StringWriter text = new StringWriter();
					VelocityEngine ve = new VelocityEngine();
					ve.mergeTemplate("error.vm", "UTF-8", velocityContext, text);
					message.setText(text.toString(), true);
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.mailSender.send(preparator);
	}

}