package com.taogen.example.springbootwithes.es.repository;

import com.taogen.example.springbootwithes.model.EsTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Taogen
 */
public interface EsTableAnnotationRepository extends ElasticsearchRepository<EsTable, String> {
    @Query("{\"match\": {\"client_name\": {\"query\": \"?0\"}}}")
    Page<EsTable> findByClientName(String clientName, Pageable pageable);

}
