package com.taogen.example.springbootwithes.es.client;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Taogen
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsRestHighLevelClientTest {
    @Autowired
    private RestHighLevelClient highLevelClient;

//    RestClient lowLevelClient = highLevelClient.getLowLevelClient();

    private static final String indexName = "rdbms_sync_idx";

    @Test
    public void findByName() throws IOException {
//        IndexRequest request = new IndexRequest(indexName)
//                .source(singletonMap("feature", "high-level-rest-client"))
//                .setRefreshPolicy(IMMEDIATE);;
//        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
//        System.out.println("response \n : "+ response);
        String clientName = "test2";
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("client_name", clientName));
        SearchHit[] searchHits = printHits(new SearchRequest(indexName).source(sourceBuilder));
        assertTrue(Arrays.asList(searchHits).stream().allMatch(e -> clientName.equals(e.getSourceAsMap().get("client_name"))));
    }

    @Test
    public void findAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchHit[] searchHits = printHits(searchRequest);
        assertNotNull(searchHits);
    }

    public SearchHit[] printHits(SearchRequest searchRequest) throws IOException {
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println("response: \n");
        Arrays.asList(searchHits).forEach(hit -> System.out.println(hit.getSourceAsString()));
        return searchHits;
    }
}
