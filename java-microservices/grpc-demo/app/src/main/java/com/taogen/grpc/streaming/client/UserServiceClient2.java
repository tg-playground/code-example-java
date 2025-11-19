package com.taogen.grpc.streaming.client;

import com.google.protobuf.StringValue;
import com.taogen.grpc.User;
import com.taogen.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author taogen
 */
public class UserServiceClient2 {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceStub stub = UserServiceGrpc.newStub(channel);

        StreamObserver<StringValue> requestObserver = new StreamObserver<StringValue>() {

            @Override
            public void onNext(StringValue value) {
                System.out.println(value.getValue());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error from server: " + t);
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed.");
            }
        };

        // Begin streaming
        StreamObserver<User> userStreamObserver = stub.addUsers(requestObserver);

        // Send several messages
        for (int i = 0; i < 3; i++) {
            User user = User.newBuilder()
                    .setName("User" + i)
                    .setAge(20 + i)
                    .setGender(i % 2 == 0 ? "Male" : "Female")
                    .build();
            userStreamObserver.onNext(user);
            Thread.sleep(3000); // Simulate delay
        }
        // Tell the server we're done
        userStreamObserver.onCompleted();

        Thread.sleep(3000); // Wait for async request to complete
        channel.shutdown();
    }
}
