package com.taogen.example.springbootwithes.es.repository;

import com.taogen.example.springbootwithes.model.EsTable;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTableAnnotationRepositoryTest {

    @Autowired
    private EsTableAnnotationRepository esTableAnnotationRepository;

    @Test
    public void findByClientName() {
        String clientName = "test";
        Page<EsTable> esTablePage = esTableAnnotationRepository
                .findByClientName(clientName, PageRequest.of(0, 10));
        System.out.println("result list: " + esTablePage.getContent().toString());
        assertTrue(esTablePage.getContent().stream().allMatch(e -> clientName.equals(e.getClientName())));
    }
}