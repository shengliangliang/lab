package com.king.mq.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqClientConsumer {
    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("win10");
        factory.setPassword("123456");
        factory.setVirtualHost("win10");
        factory.setHost("192.168.0.103");
        factory.setPort(5672);


        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();

            boolean autoAck = false;
            Channel finalChannel = channel;


            channel.basicConsume("queueName", autoAck, "myConsumerTag",
                    new DefaultConsumer(finalChannel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException
                        {
                            String routingKey = envelope.getRoutingKey();
                            String contentType = properties.getContentType();
                            long deliveryTag = envelope.getDeliveryTag();
                            // (process the message components here ...)
                            finalChannel.basicAck(deliveryTag, false);
                        }
                    });


            GetResponse response = channel.basicGet("queueName", autoAck);
            if (response == null) {
                // No message retrieved.
            } else {
                AMQP.BasicProperties props = response.getProps();
                byte[] body = response.getBody();

                System.out.println(new String(body));

                long deliveryTag = response.getEnvelope().getDeliveryTag();
                //channel.basicAck(method.deliveryTag, false); // acknowledge receipt of the message
            }
        }catch (Exception e){

        }



    }
}
