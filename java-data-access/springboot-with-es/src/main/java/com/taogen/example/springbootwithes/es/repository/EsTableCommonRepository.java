package com.taogen.example.springbootwithes.es.repository;

import com.taogen.example.springbootwithes.model.EsTable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Taogen
 */
public interface EsTableCommonRepository extends Repository<EsTable, String> {
    List<EsTable> findByClientName(String clientName);
}
