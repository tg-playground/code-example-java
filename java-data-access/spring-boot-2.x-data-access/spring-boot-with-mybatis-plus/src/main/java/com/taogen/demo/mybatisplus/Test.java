package com.taogen.demo.mybatisplus;

import com.taogen.demo.mybatisplus.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class Test {
    public static void main(String[] args) {
        String s = "倪发科、韩先聪、杨振超、陈树隆、周春雨、" +
                "陈维席、姚辉、蒋昌盛、潘奇志、汤传信、周善武、王诚、陈贵刚、刘健、从善明、周维敏、谢绍颖";
        String[] names = s.split("、");
        System.out.println(Arrays.toString(names));
        String result = Arrays.stream(names).map(item -> String.format("title like \"%%%s%%\" or content like \"%%%s%%\"", item, item))
                .collect(Collectors.joining(" or "));
        System.out.println(result);

    }

    private static List<User> buildUserList() {
        return new ArrayList<>(Arrays.asList(
                new User("tom", null, null),
                new User("test1", null, null),
                new User("jack", null, null)
        ));
    }
}
