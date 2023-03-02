package com.taogen.demo.springdataes.esjavaapiclient;

import com.taogen.demo.springdataes.AbstractBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class EsJavaApiClientUtilTest extends AbstractBaseTest {

    @Autowired
    private EsJavaApiClientUtil esJavaApiClientUtil;

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void findPage() {
        esJavaApiClientUtil.findPage();
    }

    @Test
    public void count() {
    }
}
