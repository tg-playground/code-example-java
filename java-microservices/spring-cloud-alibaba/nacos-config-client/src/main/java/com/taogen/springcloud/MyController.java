package com.taogen.springcloud;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taogen
 */
@RestController
@RequestMapping("/")
public class MyController {
    @Value("${greeting}")
    private String greeting;

    /**
     * @NacosConfig支持运行期动态更新
     */
    @NacosConfig(dataId = "nacos-config-client-dev.properties", group = "DEFAULT_GROUP", key = "greeting2")
    private String greeting2;

    @GetMapping("/greeting")
    public String greeting() {
        return greeting;
    }

    @GetMapping("/greeting2")
    public String greeting2() {
        return greeting2;
    }
}
