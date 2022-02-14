package com.taogen.example;

import com.aliyun.oss.OSS;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OssHandler {
    private final OSS ossClient;

    @Value("${app.aliyun.oss.bucketName}")
    private String bucketName;


}
