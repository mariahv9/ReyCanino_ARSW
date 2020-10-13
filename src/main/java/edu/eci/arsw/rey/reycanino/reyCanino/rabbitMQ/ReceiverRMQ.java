package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

public class ReceiverRMQ {
    public static void main(String[] args) {
        ConnectionRMQ rc = new ConnectionRMQ();
        rc.create();
        rc.receiveMesssage();
        while(true) {} // Línea para demo!
    }
}