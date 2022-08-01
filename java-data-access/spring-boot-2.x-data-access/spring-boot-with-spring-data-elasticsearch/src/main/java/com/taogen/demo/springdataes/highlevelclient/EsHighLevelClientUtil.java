package com.taogen.demo.springdataes.highlevelclient;

import com.taogen.demo.springdataes.entity.Bank;
import com.taogen.demo.springdataes.vo.QueryPage;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
@Component
public class EsHighLevelClientUtil {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public <T> String insert(T t) {
        return null;
    }

    public void delete() {

    }

    public Boolean update() {
        return null;
    }

    public Object getById(String id, String indexName) {
        GetRequest getRequest = new GetRequest(indexName, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest,
                    RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResponse;
//        SearchRequest searchRequest = new SearchRequest(indexName);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery("_id", id));
//        searchRequest.source(searchSourceBuilder);
//        System.out.println("Search JSON query: " + searchRequest.source().toString());
//        SearchResponse searchResponse = null;
//        try {
//            searchResponse = restHighLevelClient.search(searchRequest,
//                    RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return searchResponse;
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


    public <T> List<T> findPage() {
        return null;
    }

    public Long count() {
        return null;
    }

}
