package com.taogen.grpc.advanced.deadlines.client;

import com.taogen.grpc.HelloRequest;
import com.taogen.grpc.HelloResponse;
import com.taogen.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author taogen
 */
public class DeadlineClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
//                .intercept(new DefaultDeadlineInterceptor())
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        try {
            HelloResponse helloResponse = stub
                    .withDeadlineAfter(3, TimeUnit.SECONDS)
//                    .withDeadline(Deadline.after(3, TimeUnit.SECONDS))
                    .hello(HelloRequest.newBuilder()
                            .setFirstName("Thomas")
                            .setLastName("Jones")
                            .build());
            System.out.println("Response: " + helloResponse.getGreeting());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            if (e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
                System.out.println("Deadline exceeded!");
            }
            return;
        }

        channel.shutdown();

    }
}
