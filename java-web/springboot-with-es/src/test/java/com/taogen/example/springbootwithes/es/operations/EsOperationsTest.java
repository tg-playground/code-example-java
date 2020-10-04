package com.taogen.example.springbootwithes.es.operations;

import com.taogen.example.springbootwithes.model.EsTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taogen
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsOperationsTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private static final String indexName = "rdbms_sync_idx";

    @Test
    public void findById() {
        String id = "2";
        EsTable esTable = elasticsearchOperations
                .queryForObject(GetQuery.getById(id), EsTable.class);
        System.out.println(esTable);
        assertEquals(id, esTable.getId().toString());
    }

    @Test
    public void save() {
        EsTable esTable = new EsTable();
        esTable.setId(3);
        esTable.setClientName("test3");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(esTable.getId().toString())
                .withObject(esTable)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
        System.out.println("documentId: " + documentId);
        assertNotNull(documentId);
    }

}
