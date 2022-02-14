package com.taogen.example;

import com.aliyun.oss.OSS;
import com.taogen.example.config.AliyunOssProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Test
    public void testOss() {
        assertNotNull(ossClient);
        assertTrue(ossClient.doesObjectExist(aliyunOssProperties.getBucketName(),
                "examine/customer/contract/2022/2/11/87dd38469c01421293ee315e8cd0607d/icon.jpg"));
    }

    @Test
    public void test() {
        System.out.println("hello world");
    }
}
