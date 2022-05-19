package com.taogen.demo.springdatamongodb.template;

import com.taogen.demo.springdatamongodb.entity.GroceryItem;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class MongoTemplateUtilTest {

    @Autowired
    private MongoTemplateUtil mongoTemplateUtil;

    @Test
    void saveOrUpdate() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        GroceryItem groceryItem = mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        GroceryItem fetchEntity = mongoTemplateUtil.findById(groceryItem.getId(), GroceryItem.class);
        assertNotNull(fetchEntity);
        assertEquals(groceryItem.getId(), fetchEntity.getId());
    }

    private GroceryItem getRandomGroceryItem(ObjectId objectId) {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setId(objectId.toString());
        groceryItem.setName("name - " + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
        groceryItem.setQuantity(ThreadLocalRandom.current().nextInt(0, 10 + 1));
        groceryItem.setCategory("category" + ThreadLocalRandom.current().nextInt(0, 10 + 1));
        log.debug("groceryItem: {}", groceryItem);
        return groceryItem;
    }

    @Test
    void delete() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        GroceryItem groceryItem = mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        GroceryItem fetchEntity = mongoTemplateUtil.findById(groceryItem.getId(), GroceryItem.class);
        assertNotNull(fetchEntity);
        mongoTemplateUtil.delete(randomGroceryItem);
        fetchEntity = mongoTemplateUtil.findById(groceryItem.getId(), GroceryItem.class);
        assertNull(fetchEntity);
    }

    @Test
    void findById() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        GroceryItem groceryItem = mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        GroceryItem fetchEntity = mongoTemplateUtil.findById(groceryItem.getId(), GroceryItem.class);
        assertNotNull(fetchEntity);
        assertEquals(groceryItem.getId(), fetchEntity.getId());
    }

    @Test
    void findAll() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        List<GroceryItem> items = mongoTemplateUtil.findAll(GroceryItem.class);
        log.debug("item size: {}", items.size());
        assertNotNull(items);
        assertTrue(items.size() > 0);
    }

    @Test
    void count() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        long count = mongoTemplateUtil.count(GroceryItem.class);
        log.debug("count: {}", count);
        assertTrue(count > 0);
    }

    @Test
    void findByQuery() {
        ObjectId objectId = new ObjectId();
        GroceryItem randomGroceryItem = getRandomGroceryItem(objectId);
        mongoTemplateUtil.saveOrUpdate(randomGroceryItem);
        Query query = new Query();
        query.addCriteria(MongoTemplateHelper.getAndConditions(Arrays.asList(
                Criteria.where("name").is("Whole Wheat Biscuit 222"),
                Criteria.where("category").is("snacks")
        )));
        log.debug("query is: {}", query);
        List<GroceryItem> items = mongoTemplateUtil.findByQuery(query, GroceryItem.class);
        log.debug("item size: {}", items.size());
        assertNotNull(items);
        assertTrue(items.size() > 0);
    }

}
