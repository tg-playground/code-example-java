package com.taogen.example.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
public class AliyunOssConfig {

    @Bean(name = "ossClient")
    public OSS ossClient(@Autowired AliyunOssProperties properties) {
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(),
                properties.getAccessKeyId(), properties.getAccessKeySecret());
        return ossClient;
    }
}
