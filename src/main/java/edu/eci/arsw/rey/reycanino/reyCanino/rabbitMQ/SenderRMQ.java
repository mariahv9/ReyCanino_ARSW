package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import java.util.Scanner;

public class SenderRMQ {
    public static void main(String[] args) {
        ConnectionRMQ rc = new ConnectionRMQ();
        rc.create();
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while(!"end".equals(message)) {
            System.out.println("Enter a message");
            rc.sendMessage(message);
            message = keyboard.nextLine();
        }
        rc.close();
    }
}