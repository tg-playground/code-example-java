package com.taogen.demo.springdataes.repository;

import com.taogen.demo.springdataes.entity.Bank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * * index name:
 * * specify the index name in the annotation @Document(indexName = "bank") of the entity class
 *
 * @author Taogen
 */
public interface BankRepository
    extends PagingAndSortingRepository<Bank, String> {
//        extends ElasticsearchRepository<Bank, String> {

}
