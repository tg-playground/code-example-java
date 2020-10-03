package com.taogen.example.mybatisplus.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusCodeGeneratorTest {

    @Autowired
    private MyBatisPlusCodeGenerator myBatisPlusCodeGenerator;

    @Test
    public void generate() {
        assertTrue(myBatisPlusCodeGenerator.generate());
    }
}