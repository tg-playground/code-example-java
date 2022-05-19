package com.taogen.demo.springdatamongodb.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Taogen
 */
@Component
public class MongoTemplateUtil {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> T saveOrUpdate(T t, String collectionName) {
        return mongoTemplate.save(t, collectionName);
    }

    public <T> Long delete(T t, String collectionName) {
        return mongoTemplate.remove(t, collectionName).getDeletedCount();
    }

    public <T> T findById(String id, Class<T> cls, String collectionName) {
        return mongoTemplate.findById(id, cls, collectionName);
    }

    public <T> List<T> findAll(Class<T> cls, String collectionName) {
        return mongoTemplate.findAll(cls, collectionName);
    }

    public <T> Long count(Class<T> cls, String collectionName) {
        return mongoTemplate.count(new Query(), cls, collectionName);
    }

    public <T> List<T> findByQuery(Query query, Class<T> cls, String collectionName) {
        return mongoTemplate.find(query, cls, collectionName);
    }
}
