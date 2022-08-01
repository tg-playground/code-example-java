package com.taogen.demo.springdataes.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
@Component
public class EsDocumentOperationUtil {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public <T> String insert(T t, String[] indexNames) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(t)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexNames));
        return documentId;
    }

    public void delete(String id, String[] indexNames) {
        String s = elasticsearchOperations.delete(id, IndexCoordinates.of(indexNames));
    }

    public boolean update(UpdateQuery updateQuery, String[] indexNames) {
        UpdateResponse response = elasticsearchOperations.update(updateQuery, IndexCoordinates.of(indexNames));
        return response.getResult() == UpdateResponse.Result.UPDATED;
    }

    public <T> T getById(String id, Class<T> cls, String[] indexNames) {
        return elasticsearchOperations.get(id, cls, IndexCoordinates.of(indexNames));
    }

    public <T> List<T> findList(Query query, Class<T> cls, String[] indexNames) {
        SearchHits<T> searchHits = elasticsearchOperations.search(query, cls, IndexCoordinates.of(indexNames));
        if (searchHits == null) {
            return Collections.emptyList();
        }
        return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public <T> long count(Query query, String[] indexNames) {
        return elasticsearchOperations.count(query, IndexCoordinates.of(indexNames));
    }
}
