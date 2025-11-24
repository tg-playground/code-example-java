package com.taogen.grpc.advanced.interceptor.client;

import io.grpc.*;

/**
 *
 * @author taogen
 */
public class MyClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
//        System.out.println("Client Interceptor: Calling method " + method.getFullMethodName());
//        return next.newCall(method, callOptions);
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                System.out.println("Client Interceptor: Calling method " + method.getFullMethodName());
                super.start(responseListener, headers);
            }

        };
    }
}
