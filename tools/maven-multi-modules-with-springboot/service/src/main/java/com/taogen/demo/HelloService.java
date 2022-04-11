package com.taogen.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;

    public String sayHello(String name) {
        return helloDao.sayHello(name);
    }
}
