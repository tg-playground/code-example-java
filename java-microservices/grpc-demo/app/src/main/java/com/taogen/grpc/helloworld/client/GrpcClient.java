package com.taogen.grpc.helloworld.client;

import com.taogen.grpc.HelloRequest;
import com.taogen.grpc.HelloResponse;
import com.taogen.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author taogen
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Thomas")
                .setLastName("Jones")
                .build());
        System.out.println("Response: " + helloResponse.getGreeting());

        channel.shutdown();
    }
}
