package edu.eci.arsw.rey.reycanino.reyCanino.rabbitMQ;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionRMQ {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    ConnectionRMQ() {
        factory = new ConnectionFactory();
        factory.setHost(ConfigurationRMQ.RABBITMQ_SERVER);
        factory.setPort(ConfigurationRMQ.RABBITMQ_PORT);
        factory.setUsername(ConfigurationRMQ.USERNAME);
        factory.setPassword(ConfigurationRMQ.PASSWORD);
    }

    Channel create() {
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(ConfigurationRMQ.QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

    void sendMessage(String message) {
        try {
            channel.basicPublish("", ConfigurationRMQ.QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent message '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void receiveMesssage() {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Message Received! ->  " + message + "(" + envelope.getRoutingKey() + ", " + envelope.getDeliveryTag() + ")");
            }
        };
        try {
            channel.basicConsume(ConfigurationRMQ.QUEUE_NAME, true, consumer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}