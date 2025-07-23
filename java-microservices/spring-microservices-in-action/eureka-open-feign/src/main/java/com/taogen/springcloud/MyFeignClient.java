package com.taogen.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author taogen
 */
@FeignClient("eureka-client-1")
public interface MyFeignClient {
    @GetMapping("/")
    String client1();
}
