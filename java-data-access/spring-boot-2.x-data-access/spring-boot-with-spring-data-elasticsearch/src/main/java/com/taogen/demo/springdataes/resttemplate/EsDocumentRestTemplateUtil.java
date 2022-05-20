package com.taogen.demo.springdataes.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
@Component
public class EsDocumentRestTemplateUtil {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public <T> T insert(T t, String[] indexNames) {
        return elasticsearchRestTemplate.save(t, IndexCoordinates.of(indexNames));
    }

    public <T> void delete(String id, String[] indexNames) {
        String s = elasticsearchRestTemplate.delete(id, IndexCoordinates.of(indexNames));
    }

    public boolean update(UpdateQuery updateQuery, String[] indexNames) {
        UpdateResponse response = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(indexNames));
        return response.getResult() == UpdateResponse.Result.UPDATED;
    }

    public <T> T getById(String id, Class<T> cls, String[] indexNames) {
        return elasticsearchRestTemplate.get(id, cls, IndexCoordinates.of(indexNames));
    }

    public <T> List<T> findList(Query query, Class<T> cls, String[] indexNames) {
        SearchHits<T> searchHits = elasticsearchRestTemplate.search(query, cls, IndexCoordinates.of(indexNames));
        if (searchHits == null) {
            return Collections.emptyList();
        }
        return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public <T> long count(Query query, String[] indexNames) {
        return elasticsearchRestTemplate.count(query, IndexCoordinates.of(indexNames));
    }
}
