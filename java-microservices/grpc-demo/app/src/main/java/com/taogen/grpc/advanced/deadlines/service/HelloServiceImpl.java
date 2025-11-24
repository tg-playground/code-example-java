package com.taogen.grpc.advanced.deadlines.service;

import com.google.common.util.concurrent.MoreExecutors;
import com.taogen.grpc.HelloRequest;
import com.taogen.grpc.HelloResponse;
import com.taogen.grpc.HelloServiceGrpc;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;

/**
 * @author taogen
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        Context ctx = Context.current();
        ctx.addListener(context -> {
            if (context.isCancelled()) {
                System.out.println("Request was cancelled due to deadline exceeded.");
            }
        }, MoreExecutors.directExecutor());
        try {
            Thread.sleep(5000); // Simulate a long processing time of 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Processing completed within deadline.");
        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Response sent to client.");
    }
}
