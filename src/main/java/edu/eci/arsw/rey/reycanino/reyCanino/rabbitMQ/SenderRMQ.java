package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.service.ReyCaninoService;

import java.util.Date;

public class SenderRMQ {
//    public static void main(String[] args) {
//        ConnectionRMQ rc = new ConnectionRMQ();
//        rc.create();
//        String message = messageSend();
//        if (message != ""){
//            rc.sendMessage(message);
//        }
//        rc.close();
//    }

    public static String messageSend (String message){
        ConnectionRMQ rc = new ConnectionRMQ();
        rc.create();
        String m = "";
        if (message != null){
            m += message;
            rc.sendMessage(m);
        }
        rc.close();
        return m;
    }
}