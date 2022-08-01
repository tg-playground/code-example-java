package com.taogen.demo.springdataes.highlevelclient;

import com.taogen.demo.springdataes.AbstractBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class EsHighLevelClientUtilTest extends AbstractBaseTest {

    @Autowired
    private EsHighLevelClientUtil esHighLevelClientUtil;

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
        getRandomEntityWithoutId();
        Object response = esHighLevelClientUtil.getById("xrBRr3gBJ31jbO9fnE3r", "bank");
        log.info("response: {}", response);
    }

    @Test
    public void findPage() {
    }

    @Test
    public void count() {
    }
}
