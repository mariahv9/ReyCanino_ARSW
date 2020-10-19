package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import com.rabbitmq.client.*;
import java.io.IOException;

public class ReceiverRMQ {
    public static void recibir (String message) {
        String[] values = message.split("|");
        String[] data = values[1].split(",");
        if (values[0].equals("reservar")){
        }else if (values[0].equals("confirmar")) {

        }
    }

    public static void receiveMesssage() {
        Channel channel = ConnectionRMQ.create();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                recibir(message);
                System.out.println("Su reserva se ha enviado! Revise su correo ->  " + message + "(" + envelope.getRoutingKey() + ", " + envelope.getDeliveryTag() + ")");
            }
        };
        try {
            channel.basicConsume(ConfigurationRMQ.QUEUE_NAME, true, consumer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}