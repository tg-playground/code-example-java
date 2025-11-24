package com.taogen.grpc.advanced.deadlines;

import com.taogen.grpc.advanced.deadlines.service.HelloServiceImpl;
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
                .build();

        server.start();
        System.out.println("gRPC server started on port 8080");
        server.awaitTermination();
    }
}
