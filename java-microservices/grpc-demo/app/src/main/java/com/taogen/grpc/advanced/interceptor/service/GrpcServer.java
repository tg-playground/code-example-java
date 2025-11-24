package com.taogen.grpc.advanced.interceptor.service;

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
                .intercept(new AroundServerInterceptor())
                .build();

        server.start();
        System.out.println("gRPC server started on port 8080");
        server.awaitTermination();
    }
}
