package com.taogen.example.mybatisplus.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MyBatisPlusCodeGeneratorTest {

    @Autowired
    private MyBatisPlusCodeGenerator myBatisPlusCodeGenerator;

    @Test
    public void generate() {
        assertTrue(myBatisPlusCodeGenerator.generate());
    }
}