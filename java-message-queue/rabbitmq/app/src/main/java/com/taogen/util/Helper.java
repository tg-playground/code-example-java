package com.taogen.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author taogen
 */
@Slf4j
public class Helper {
    public static Connection getConnection() throws IOException, TimeoutException {
        log.debug("To create a RabbitMQ connection...");
        // Example usage of RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        Dotenv dotenv = Dotenv.load();
        String userName = dotenv.get("RABBITMQ_USER");
        String password = dotenv.get("RABBITMQ_PASSWORD");
        String virtualHost = "/";
        String hostName = "localhost";
        int portNumber = Integer.parseInt(dotenv.get("RABBITMQ_PORT"));
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);

        Connection conn = factory.newConnection();
        log.debug("Connection: {}", conn);
        return conn;
    }
}
