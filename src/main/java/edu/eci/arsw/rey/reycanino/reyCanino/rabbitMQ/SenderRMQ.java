package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;

public class SenderRMQ {
    public static String reservar (Horario horario){
        ConnectionRMQ rc = new ConnectionRMQ();
        rc.create();
        String m = "";
        if (horario != null){
            m += "reservar|";
            m += horario.getId();
            m += horario.getReserva().getCliente() + ",";
            m += horario.getReserva().getCorreo() + ",";
            m += horario.getReserva().getMascota() + ",";
            m += horario.getReserva().getComentario() + ",";
            m += horario.getReserva().getTelefono() + ",";
            m += horario.getReserva().getRaza();
            rc.sendMessage(m);
        }
        rc.close();
        return m;
    }

    public static String confirmar (Horario horario){
        ConnectionRMQ rc = new ConnectionRMQ();
        rc.create();
        String m = "";
        if (horario != null){
            m += "confirmar|";
            m += horario.getId() + ",";
            m += horario.getReserva().getCliente() + ",";
            m += horario.getReserva().getCorreo() + ",";
            m += horario.getReserva().getMascota() + ",";
            m += horario.getReserva().getComentario() + ",";
            m += horario.getReserva().getTelefono() + ",";
            m += horario.getReserva().getRaza();
            rc.sendMessage(m);
        }
        rc.close();
        return m;
    }
}