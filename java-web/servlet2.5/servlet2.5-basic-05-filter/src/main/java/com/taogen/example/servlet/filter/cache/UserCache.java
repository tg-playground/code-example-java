package com.taogen.example.servlet.filter.cache;

import com.taogen.example.servlet.filter.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserCache {
    public static Map<String, User> userCache = new ConcurrentHashMap<>();
}
