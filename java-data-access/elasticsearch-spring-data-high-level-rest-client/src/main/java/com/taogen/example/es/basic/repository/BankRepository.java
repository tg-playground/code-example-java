package com.taogen.example.es.basic.repository;

import com.taogen.example.es.basic.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.Repository;

/**
 * @author Taogen
 */
public interface BankRepository extends Repository<Bank, Long> {
    Bank findById(Long id);

    @Query("{\"match\": {\"firstname\": {\"query\": \"?0\"}}}")
    Page<Bank> findByFirstNameWithAnnotation(String firstName, Pageable pageable);
}
