package com.taogen.grpc.interceptor.interceptor.client;

import io.grpc.*;

/**
 *
 * @author taogen
 */
public class AroundClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        System.out.println("[Client] Calling method: " + method.getFullMethodName());

        // Wrap the original ClientCall
        ClientCall<ReqT, RespT> clientCall = next.newCall(method, callOptions);

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(clientCall) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                System.out.println("[Client] Sending headers: " + headers);

                // Wrap the listener for response events
                Listener<RespT> listener = new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {

                    @Override
                    public void onHeaders(Metadata headers) {
                        System.out.println("[Client] Received response headers: " + headers);
                        super.onHeaders(headers);
                    }

                    @Override
                    public void onMessage(RespT message) {
                        System.out.println("[Client] Received response message: " + message);
                        super.onMessage(message);
                    }

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        System.out.println("[Client] Call closed with status: " + status);
                        System.out.println("[Client] Trailers: " + trailers);
                        super.onClose(status, trailers);
                    }
                };

                super.start(listener, headers);
            }

            @Override
            public void sendMessage(ReqT message) {
                System.out.println("[Client] Sending request message: " + message);
                super.sendMessage(message);
            }
        };
    }
}
