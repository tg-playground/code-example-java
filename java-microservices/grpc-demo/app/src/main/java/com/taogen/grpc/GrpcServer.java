package com.taogen.grpc;

import com.taogen.grpc.helloworld.service.HelloServiceImpl;
import com.taogen.grpc.streaming.service.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author taogen
 */
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceImpl())
                .addService(new UserServiceImpl())
                .build();

        server.start();
        System.out.println("gRPC server started on port 8080");
        server.awaitTermination();
    }
}
