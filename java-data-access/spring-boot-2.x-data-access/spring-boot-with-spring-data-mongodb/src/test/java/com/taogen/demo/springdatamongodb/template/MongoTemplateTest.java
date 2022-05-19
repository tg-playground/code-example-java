package com.taogen.demo.springdatamongodb.template;

import com.mongodb.client.result.DeleteResult;
import com.taogen.demo.springdatamongodb.entity.GroceryItem;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class MongoTemplateTest {
    public static final String COLLECTION_NAME = "groceryitems";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
    }

    public GroceryItem insertRandomEntityById(ObjectId objectId) {
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        GroceryItem insertEntity = mongoTemplate.save(new GroceryItem(
                objectId.toString(), "Whole Wheat Biscuit - " + datetime, 5, "snacks"));
        log.debug("insert entity: {}", insertEntity);
        assertNotNull(insertEntity);
        assertEquals(objectId.toString(), insertEntity.getId());
        return insertEntity;
    }

    @Test
    public void delete() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
        GroceryItem fetchEntity = mongoTemplate.findById(objectId.toString(), GroceryItem.class);
        assertNotNull(fetchEntity);
        DeleteResult deleteResult = mongoTemplate.remove(new GroceryItem(objectId.toString()), COLLECTION_NAME);
//        DeleteResult deleteResult = mongoTemplate.getCollection(COLLECTION_NAME).deleteOne(new Document("_id", objectId));
        long deletedCount = deleteResult.getDeletedCount();
        assertEquals(1, deletedCount);
    }

    @Test
    void update() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
        GroceryItem fetchEntity = mongoTemplate.findById(objectId.toString(), GroceryItem.class);
        String newName = fetchEntity.getName() + "new" + new Date();
        fetchEntity.setName(newName);
        GroceryItem updateEntity = mongoTemplate.save(fetchEntity);
        GroceryItem fetchEntity2 = mongoTemplate.findById(objectId.toString(), GroceryItem.class);
        assertEquals(newName, fetchEntity2.getName());
    }

    @Test
    void findById() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
        GroceryItem fetchEntity = mongoTemplate.findById(objectId.toString(), GroceryItem.class);
        assertNotNull(fetchEntity);
    }

    @Test
    void findAll() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
        List<GroceryItem> groceryitems = mongoTemplate.findAll(GroceryItem.class, COLLECTION_NAME);
        log.debug("groceryitems size: {}", groceryitems.size());
        assertNotNull(groceryitems);
        assertTrue(groceryitems.size() > 0);
    }


    @Test
    void findByName() {
        Query query = new Query();
        // query is: { "name" : "Whole Wheat Biscuit 222", "category" : "snacks"}, Fields: {}, Sort: {}
        query.addCriteria(Criteria.where("name").is("Whole Wheat Biscuit 222"));
        query.addCriteria(Criteria.where("category").is("snacks"));
        // query is: { "$and" : [{ "name" : "Whole Wheat Biscuit 222"}, { "category" : "snacks"}]}, Fields: {}, Sort: {}
//        query.addCriteria(new Criteria().andOperator(Arrays.asList(
//                Criteria.where("name").is("Whole Wheat Biscuit 222"),
//                Criteria.where("category").is("snacks")
//        )));
        // query is: { "$or" : [{ "name" : "Whole Wheat Biscuit 222"}, { "category" : "snacks"}]}, Fields: {}, Sort: {}
//        query.addCriteria(new Criteria().orOperator(Arrays.asList(
//                Criteria.where("name").is("Whole Wheat Biscuit 222"),
//                Criteria.where("category").is("snacks")
//        )));
        log.debug("query is: {}", query);
        List<GroceryItem> groceryItems = mongoTemplate.find(query, GroceryItem.class, COLLECTION_NAME);
        log.debug("groceryItems size: {}", groceryItems.size());
    }

    @Test
    void count() {
        ObjectId objectId = new ObjectId();
        insertRandomEntityById(objectId);
        long count = mongoTemplate.count(new Query(), GroceryItem.class, COLLECTION_NAME);
        log.debug("count: {}", count);
        assertTrue(count > 1);
    }
}
