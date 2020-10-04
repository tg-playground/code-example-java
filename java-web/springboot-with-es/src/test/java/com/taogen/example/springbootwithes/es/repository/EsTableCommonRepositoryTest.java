package com.taogen.example.springbootwithes.es.repository;

import com.taogen.example.springbootwithes.model.EsTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTableCommonRepositoryTest {

    @Autowired
    private EsTableCommonRepository esTableCommonRepository;

    @Test
    public void findByClientName() {
        String clientName = "test";
        List<EsTable> entities = esTableCommonRepository.findByClientName(clientName);
        System.out.println("result list: " + entities);
        assertTrue(entities.stream().allMatch(e -> clientName.equals(e.getClientName())));
    }
}