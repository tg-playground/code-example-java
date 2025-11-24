package com.taogen.grpc.advanced.interceptor.client;

import com.google.protobuf.StringValue;
import com.taogen.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author taogen
 */
public class UserServiceClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .intercept(new AroundClientInterceptor(), new MyClientInterceptor())
                .build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        stub.searchUsers(StringValue.newBuilder().setValue("Alice").build())
                .forEachRemaining(userResponse -> {
                    System.out.println("User ID: " + userResponse.getId());
                    System.out.println("User Name: " + userResponse.getName());
                    System.out.println("User Age: " + userResponse.getAge());
                    System.out.println("User Gender: " + userResponse.getGender());
                    System.out.println("-----");
                });

        channel.shutdown();
    }
}
