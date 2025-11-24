package com.taogen.grpc.interceptor.interceptor.service;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

/**
 *
 * @author taogen
 */
public class MyServerInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        System.out.println("Incoming call: " + call.getMethodDescriptor().getFullMethodName());
        System.out.println("Headers: " + headers);

        // Continue to the next interceptor or actual service
        return next.startCall(call, headers);
    }
}
