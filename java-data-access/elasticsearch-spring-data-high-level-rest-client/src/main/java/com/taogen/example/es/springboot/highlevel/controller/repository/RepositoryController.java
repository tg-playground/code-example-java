package com.taogen.example.es.springboot.highlevel.controller.repository;

import com.taogen.example.es.springboot.highlevel.entity.Bank;
import com.taogen.example.es.springboot.highlevel.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/repository")
public class RepositoryController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/bank/{id}")
    public Bank findById(@PathVariable("id") Long id) {
        Bank bank = bankRepository.findById(id);
        return bank;
    }

    @GetMapping("/bank/findByFirstName")
    public Page<Bank> findByFirstName(String firstName, Pageable page) {
        Page<Bank> banks = bankRepository.findByFirstNameWithAnnotation(firstName, page);
        return banks;
    }
}
