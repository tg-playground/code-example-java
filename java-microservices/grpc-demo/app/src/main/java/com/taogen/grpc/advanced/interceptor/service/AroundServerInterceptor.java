package com.taogen.grpc.advanced.interceptor.service;

import io.grpc.*;

/**
 *
 * @author taogen
 */
public class AroundServerInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        System.out.println("[Server] Method called: " + call.getMethodDescriptor().getFullMethodName());
        System.out.println("[Server] Request headers: " + headers);

        // Wrap the ServerCall to log outgoing responses
        ServerCall<ReqT, RespT> loggingCall =
                new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {

                    @Override
                    public void sendHeaders(Metadata responseHeaders) {
                        System.out.println("[Server] Sending response headers: " + responseHeaders);
                        super.sendHeaders(responseHeaders);
                    }

                    @Override
                    public void sendMessage(RespT message) {
                        System.out.println("[Server] Sending response message: " + message);
                        super.sendMessage(message);
                    }

                    @Override
                    public void close(Status status, Metadata trailers) {
                        System.out.println("[Server] Call closed with status: " + status);
                        System.out.println("[Server] Trailers: " + trailers);
                        super.close(status, trailers);
                    }
                };

        // Wrap the listener to log incoming requests
        ServerCall.Listener<ReqT> listener = next.startCall(loggingCall, headers);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {

            @Override
            public void onMessage(ReqT message) {
                System.out.println("[Server] Received request message: " + message);
                super.onMessage(message);
            }

            @Override
            public void onHalfClose() {
                System.out.println("[Server] Client finished sending messages.");
                super.onHalfClose();
            }

            @Override
            public void onCancel() {
                System.out.println("[Server] Call cancelled by client.");
                super.onCancel();
            }

            @Override
            public void onComplete() {
                System.out.println("[Server] Call completed.");
                super.onComplete();
            }

            @Override
            public void onReady() {
                System.out.println("[Server] Ready to send/receive messages.");
                super.onReady();
            }
        };
    }
}
