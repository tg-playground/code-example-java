package com.taogen.grpc.advanced.deadlines.client;

import io.grpc.*;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author taogen
 */
public class DefaultDeadlineInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        CallOptions updated = callOptions.withDeadlineAfter(2, TimeUnit.SECONDS);
        return next.newCall(method, updated);
    }
}
