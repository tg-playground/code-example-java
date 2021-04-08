package com.taogen.example.es.springboot.highlevelrestclient.controller.search;

import com.taogen.example.es.springboot.highlevelrestclient.controller.BaseController;
import com.taogen.example.es.springboot.highlevelrestclient.entity.Bank;
import com.taogen.example.es.springboot.highlevelrestclient.entity.QueryPage;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Search API
 *
 * @author Taogen
 */
@RestController
@RequestMapping("/banks")
public class SearchController extends BaseController {
    private static String INDEX_NAME = "bank";

    @GetMapping
    public ResponseEntity search(@RequestBody QueryPage<Bank> page) throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = getSearchSourceBuilder(page);
        searchRequest.source(searchSourceBuilder);
        System.out.println("Search JSON query: " + searchRequest.source().toString());
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(searchResponse);
    }

    private SearchSourceBuilder getSearchSourceBuilder(QueryPage<Bank> page) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Bank bank = page.getEntity();
        if (bank != null) {
            if (bank.getAccountNumber() != null) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("account_number", bank.getAccountNumber()));
            }
            if (bank.getAddress() != null) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("address", bank.getAddress()));
            }
            if (bank.getAge() != null) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("age", bank.getAge()));
            }
            if (bank.getBalance() != null) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("balance", bank.getBalance()));
            }
        }
        if (page.getPageNo() != null && page.getPageSize() != null) {
            searchSourceBuilder.from((page.getPageNo() - 1) * page.getPageSize());
            searchSourceBuilder.size(page.getPageSize());
        }
        if (page.getOrderBy() != null && !page.getOrderBy().isEmpty()) {
            List<String> orderBySplit = Arrays.asList(page.getOrderBy().split(" "))
                    .stream()
                    .map(item -> item.trim())
                    .filter(item -> !"".equals(item))
                    .collect(Collectors.toList());
            if (orderBySplit.size() >= 2) {
                SortOrder sortOrder = SortOrder.ASC;
                if ("desc".equalsIgnoreCase(orderBySplit.get(1))) {
                    sortOrder = SortOrder.DESC;
                }
                searchSourceBuilder.sort(new FieldSortBuilder(orderBySplit.get(0)).order(sortOrder));
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        return searchSourceBuilder;
    }
}
