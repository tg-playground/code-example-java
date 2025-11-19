package com.taogen.grpc.streaming.client;

import com.taogen.grpc.UpdateStatus;
import com.taogen.grpc.User;
import com.taogen.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 *
 * @author taogen
 */
public class UserServiceClient3 {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceStub stub = UserServiceGrpc.newStub(channel);

        StreamObserver<UpdateStatus> requestObserver = new StreamObserver<UpdateStatus>() {

            @Override
            public void onNext(UpdateStatus value) {
                System.out.println(value.toString());
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
        StreamObserver<User> userStreamObserver = stub.updateUsers(requestObserver);

        // Send several messages
        for (int i = 0; i < 3; i++) {
            User user = User.newBuilder()
                    .setId(i + 1)
                    .setName("User" + i)
                    .setAge(20 + i)
                    .setGender(i % 2 == 0 ? "Male" : "Female")
                    .build();
            userStreamObserver.onNext(user);
        }

        User notExistUser = User.newBuilder()
                .setId(-1)
                .setName("NotExistUser")
                .setAge(20)
                .setGender("Male")
                .build();
        userStreamObserver.onNext(notExistUser);
        // Tell the server we're done
        userStreamObserver.onCompleted();

        Thread.sleep(3000); // Wait for async request to complete
        channel.shutdown();
    }
}
