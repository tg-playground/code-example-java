package com.taogen.example.es.springdata.transport.controller.operation;

import com.taogen.example.es.springdata.transport.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/restTemplate")
public class OperationController {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @GetMapping("/bank/{id}")
    public Bank findById(@PathVariable("id")  Long id) {
        Bank bank = elasticsearchOperations
                .queryForObject(GetQuery.getById(id.toString()), Bank.class);
        return bank;
    }
}
