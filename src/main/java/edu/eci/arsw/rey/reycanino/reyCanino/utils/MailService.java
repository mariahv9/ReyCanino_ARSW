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
import edu.eci.arsw.rey.reycanino.reyCanino.model.TiendaCanina;

@Service("Mail")
public class MailService {

    //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Horario horario) {
        System.out.println(horario);
        MimeMessagePreparator preparator = null;
        try {
            final TiendaCanina shop = DataBaseConnection.buscarTiendaCanina(horario.getTiendaCanina());
            final Horario horarioDB = DataBaseConnection.buscarHorario(horario.getId());
            System.out.println(horario);
            System.out.println(horarioDB);
            System.out.println(shop);
            preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(horario.getReserva().getCorreo());
                    message.setFrom("reycaninostore@gmail.com");
                    message.setSubject("¡Confirma tu reserva!");
                    VelocityContext velocityContext = new VelocityContext();
                    velocityContext.put("user", horario.getReserva().getCliente());
                    velocityContext.put("codigo", horario.getId());
                    velocityContext.put("nombreTienda", shop.getNombre());
                    velocityContext.put("direccion", shop.getDireccion());
                    velocityContext.put("nombreServicio", horarioDB.getServicio());
                    String dateAux = "";
                    int dia = horarioDB.getFi().getDayOfMonth();
                    int mes = horarioDB.getFi().getMonth().getValue();
                    int anio = horarioDB.getFi().getYear();
                    int hora = horarioDB.getFi().getHour();
                    int minuto = horarioDB.getFi().getMinute();
                    dateAux = dia + " " + mes + " " + anio + " " + hora + " " + minuto;
                    System.out.println(dateAux);
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
}