package com.taogen.demo.springdataes.resttemplate;

import com.taogen.demo.springdataes.entity.Bank;
import com.taogen.demo.springdataes.util.JacksonJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class EsDocumentRestTemplateUtilTest {

    public static final String INDEX_NAME = "bank1";

    @Autowired
    private EsDocumentRestTemplateUtil esDocumentRestTemplateUtil;

    @Test
    void insert() {
        insertRandomEntity();
    }

    Bank insertRandomEntity() {
        Bank bank = getRandomEntityWithoutId();
        esDocumentRestTemplateUtil.insert(bank, new String[]{INDEX_NAME});
        log.debug("bank: {}", bank);
        assertNotNull(bank);
        assertNotNull(bank.getId());
        return bank;
    }

    private Bank getRandomEntityWithoutId() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        Bank bank = new Bank();
        bank.setAccountNumber(ThreadLocalRandom.current().nextLong(10000000000L, 100000000000L));
        bank.setAddress("address" + dateFormat.format(date));
        bank.setAge(ThreadLocalRandom.current().nextInt(0, 100 + 1));
        bank.setBalance(ThreadLocalRandom.current().nextLong(0, 100000000));
        bank.setCity("city" + dateFormat.format(date));
        bank.setEmail("email" + dateFormat.format(date));
        bank.setEmployer("employer" + dateFormat.format(date));
        bank.setFirstName("firstName" + dateFormat.format(date));
        bank.setGender("male");
        bank.setLastName("Jones");
        bank.setState("state" + dateFormat.format(date));
        return bank;
    }

    @Test
    void delete() {
        Bank bank = insertRandomEntity();
        esDocumentRestTemplateUtil.delete(bank.getId(), new String[]{INDEX_NAME});
        Bank fetchEntity = esDocumentRestTemplateUtil.getById(bank.getId(), Bank.class, new String[]{INDEX_NAME});
        assertNull(fetchEntity);
    }

    @Test
    void update() {
        Bank bank = insertRandomEntity();
        bank.setFirstName("firstName-updated");
        Document document = Document.from(JacksonJsonUtil.objectToMap(bank));
        document.setId(bank.getId());
        document.setIndex(INDEX_NAME);
        UpdateQuery updateQuery = UpdateQuery.builder(bank.getId())
                .withDocument(document)
                .build();
//        NativeSearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchQuery("email", "1bradshawmckenzie@euron.com"))
//                .build();
//        UpdateQuery updateQuery = UpdateQuery.builder(query);
        boolean updateResult = esDocumentRestTemplateUtil.update(updateQuery, new String[]{INDEX_NAME});
        assertTrue(updateResult);
    }

    @Test
    void getById() {
        Bank bank = insertRandomEntity();
        Bank fetchBank = esDocumentRestTemplateUtil.getById(bank.getId(), Bank.class, new String[]{INDEX_NAME});
        log.debug("fetchBank: {}", fetchBank);
        assertNotNull(fetchBank);
        assertEquals(bank.getId(), fetchBank.getId());
    }

    @Test
    void findList() throws InterruptedException {
        Bank bank = insertRandomEntity();
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("_id", bank.getId()))
                .build();
        // waiting for indexing to finish
        Thread.sleep(1000);
        List<Bank> list = esDocumentRestTemplateUtil.findList(query, Bank.class, new String[]{INDEX_NAME});
        log.debug("list: {}", list);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void count() {
        insertRandomEntity();
        long count = esDocumentRestTemplateUtil.count(new NativeSearchQueryBuilder().build(), new String[]{INDEX_NAME});
        log.debug("count: {}", count);
        assertTrue(count > 0);
    }
}
