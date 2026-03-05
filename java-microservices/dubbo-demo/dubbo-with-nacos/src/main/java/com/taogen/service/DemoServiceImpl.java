package com.taogen.service;

import org.apache.dubbo.config.annotation.DubboService;

/**
 *
 * @author taogen
 */
@DubboService
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
