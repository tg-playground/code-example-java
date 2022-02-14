package com.taogen.example.controller;

import com.aliyun.oss.OSS;
import com.taogen.example.config.AliyunOssProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
public class OssController {
    private final OSS ossClient;

    private final AliyunOssProperties aliyunOssProperties;

    /**
     * Check whether the oss file exists.
     *
     * @param fileUrl e.g. examine/customer/contract/2022/2/11/87dd38469c01421293ee315e8cd0607d/icon.jpg
     * @return
     */
    @GetMapping("/doesOssFileExist")
    public Boolean doesOssFileExist(String fileUrl) {
        return ossClient.doesObjectExist(aliyunOssProperties.getBucketName(),
                fileUrl);
    }
}
