package com.taogen.demo.springdataes.repository;

import com.taogen.demo.springdataes.AbstractBaseTest;
import com.taogen.demo.springdataes.entity.Bank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class BankRepositoryTest extends AbstractBaseTest {
    @Autowired
    private BankRepository bankRepository;

    @Test
    public void insert() {
        insertRandomEntity();
    }

    private void insertRandomEntity() {
        Bank bank = getRandomEntityWithoutId();
        bankRepository.save(bank);
        log.debug("insert a bank: {}", bank);
    }

    @Test
    public void delete() {
        Bank bank = getRandomEntityWithoutId();
        bankRepository.save(bank);
        Bank fetchEntity1 = bankRepository.findById(bank.getId()).orElse(null);
        assertNotNull(fetchEntity1);
        bankRepository.delete(bank);
        Bank fetchEntity2 = bankRepository.findById(bank.getId()).orElse(null);
        assertNull(fetchEntity2);
    }

    @Test
    public void update() {
        String newName = "newName" + System.currentTimeMillis();
        Bank bank = getRandomEntityWithoutId();
        bankRepository.save(bank);
        Bank fetchEntity1 = bankRepository.findById(bank.getId()).orElse(null);
        assertNotNull(fetchEntity1);
        assertNotEquals(newName, fetchEntity1.getFirstName());
        bank.setFirstName(newName);
        bankRepository.save(bank);
        Bank fetchEntity2 = bankRepository.findById(bank.getId()).orElse(null);
        assertEquals(newName, fetchEntity2.getFirstName());
    }

    @Test
    public void getById() {
        Bank bank = getRandomEntityWithoutId();
        bankRepository.save(bank);
        Bank fetchEntity = bankRepository.findById(bank.getId()).orElse(null);
        assertNotNull(fetchEntity);
        assertTrue(Objects.deepEquals(bank, fetchEntity));
    }

    @Test
    public void findPage() {
        insertRandomEntity();
        Page<Bank> bankPage = bankRepository.findAll(
                PageRequest.of(0, 10,
                        Sort.by(Sort.Direction.DESC, "age")));
        assertNotNull(bankPage);
        assertFalse(bankPage.isEmpty());
        log.info("list: {}", bankPage.getContent());
        log.info("total size: {}", bankPage.getTotalElements());
    }

    @Test
    public void count() {
        insertRandomEntity();
        long count = bankRepository.count();
        assertTrue(count > 0);
        log.info("count: {}", count);
    }
}
