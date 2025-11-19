package com.taogen.grpc.streaming.service;

import com.google.protobuf.StringValue;
import com.taogen.grpc.UpdateStatus;
import com.taogen.grpc.User;
import com.taogen.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author taogen
 */
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private static final Map<Integer, User> userMap = new HashMap<>();

    static {
        userMap.put(1, User.newBuilder().setId(1).setName("Alice").setAge(18).setGender("Female").build());
        userMap.put(2, User.newBuilder().setId(2).setName("Bob").setAge(20).setGender("Male").build());
        userMap.put(3, User.newBuilder().setId(3).setName("Cathy").setAge(19).setGender("Female").build());
        userMap.put(4, User.newBuilder().setId(4).setName("Alice").setAge(18).setGender("Female").build());
        userMap.put(5, User.newBuilder().setId(5).setName("Alice").setAge(18).setGender("Female").build());
    }

    @Override
    public void searchUsers(StringValue request, StreamObserver<User> responseObserver) {
        userMap.values().stream()
                .filter(user -> user.getName().equals(request.getValue()))
                .forEach(user -> {
                    responseObserver.onNext(user); // Stream each matching user
                    try {
                        Thread.sleep(3000); // Simulate delay
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<User> addUsers(StreamObserver<StringValue> responseObserver) {
        return new StreamObserver<>() {
            List<Integer> ids = new ArrayList<>();

            @Override
            public void onNext(User value) {
                System.out.println("Received user: " + value);
                int maxId = userMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
                int id = maxId + 1;
                ids.add(id);
                User newUser = User.newBuilder()
                        .setId(id)
                        .setName(value.getName())
                        .setAge(value.getAge())
                        .setGender(value.getGender())
                        .build();
                userMap.put(id, newUser);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Add users failed: " + t);
            }

            @Override
            public void onCompleted() {
                System.out.println("All users added.");
                StringValue response = StringValue.newBuilder()
                        .setValue(ids.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(",")))
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<User> updateUsers(StreamObserver<UpdateStatus> responseObserver) {
        return new StreamObserver<>() {
            List<UpdateStatus> updateStatusList = new ArrayList<>();

            @Override
            public void onNext(User value) {
                System.out.println("Received user: " + value);
                if (userMap.containsKey(value.getId())) {
                    userMap.put(value.getId(), value);
                    updateStatusList.add(UpdateStatus.newBuilder()
                            .setId(value.getId())
                            .setStatus("OK")
                            .setMessage("Update successfully")
                            .build());
                } else {
                    updateStatusList.add(UpdateStatus.newBuilder()
                            .setId(value.getId())
                            .setStatus("Error")
                            .setMessage("User not found")
                            .build());
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Update users failed: " + t);
            }

            @Override
            public void onCompleted() {
                System.out.println("All users updated.");
                for (UpdateStatus updateStatus : updateStatusList) {
                    responseObserver.onNext(updateStatus);
                }
                responseObserver.onCompleted();
            }
        };
    }
}
