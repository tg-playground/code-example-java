package com.taogen.example.bestpractice.statements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConditionWithEnumTest {

    @Test
    public void judge() {
        String roleName = "ROLE_ROOT_ADMIN";
        String result = "ROLE_ROOT_ADMIN: has AAA permission";
        assertEquals(result, ConditionWithEnum.judge(roleName));
    }
}