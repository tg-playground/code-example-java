package com.taogen.demo.springdataes.resttemplate;

import com.taogen.demo.springdataes.AbstractBaseTest;
import com.taogen.demo.springdataes.entity.Bank;
import com.taogen.demo.springdataes.util.JacksonJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class EsDocumentOperationUtilTest extends AbstractBaseTest {
    public static final String INDEX_NAME = "bank1";

    @Autowired
    private EsDocumentOperationUtil esDocumentOperationUtil;

    @Test
    public void insert() {
        insertRandomEntity();
    }

    Bank insertRandomEntity() {
        Bank bank = getRandomEntityWithoutId();
        String id = esDocumentOperationUtil.insert(bank, new String[]{INDEX_NAME});
        log.debug("bank: {}", id);
        assertNotNull(id);
        bank.setId(id);
        return bank;
    }

    @Test
    public void delete() {
        Bank bank = insertRandomEntity();
        esDocumentOperationUtil.delete(bank.getId(), new String[]{INDEX_NAME});
        Bank fetchEntity = esDocumentOperationUtil.getById(bank.getId(), Bank.class, new String[]{INDEX_NAME});
        assertNull(fetchEntity);
    }

    @Test
    public void update() {
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
        boolean updateResult = esDocumentOperationUtil.update(updateQuery, new String[]{INDEX_NAME});
        assertTrue(updateResult);
    }

    @Test
    public void getById() {
        Bank bank = insertRandomEntity();
        Bank fetchBank = esDocumentOperationUtil.getById(bank.getId(), Bank.class, new String[]{INDEX_NAME});
        log.debug("fetchBank: {}", fetchBank);
        assertNotNull(fetchBank);
        assertEquals(bank.getId(), fetchBank.getId());
    }

    @Test
    public void findPage() {
        Bank bank = insertRandomEntity();
        // waiting for indexing to finish
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Query query = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0, 10,
                        Sort.by(Sort.Direction.DESC, "age")))
//                .withQuery(QueryBuilders.matchQuery("_id", bank.getId()))
                .build();
        List<Bank> list = esDocumentOperationUtil.findList(query, Bank.class, new String[]{INDEX_NAME});
        log.debug("list: {}", list);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void findList() throws InterruptedException {
        Bank bank = insertRandomEntity();
        // waiting for indexing to finish
        Thread.sleep(1000);
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("_id", bank.getId()))
                .build();
        List<Bank> list = esDocumentOperationUtil.findList(query, Bank.class, new String[]{INDEX_NAME});
        log.debug("list: {}", list);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void count() {
        insertRandomEntity();
        long count = esDocumentOperationUtil.count(new NativeSearchQueryBuilder().build(), new String[]{INDEX_NAME});
        log.debug("count: {}", count);
        assertTrue(count > 0);
    }
}
