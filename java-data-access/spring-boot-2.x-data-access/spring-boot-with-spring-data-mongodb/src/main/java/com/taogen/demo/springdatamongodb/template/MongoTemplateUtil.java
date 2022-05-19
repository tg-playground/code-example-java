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

    public <T> T saveOrUpdate(T t) {
        return mongoTemplate.save(t);
        // Don't need passing collectionName argument. It declared in @Document("yourCollectionName") on entity class
        // return mongoTemplate.save(t, collectionName);
    }

    public <T> Long delete(T t) {
        return mongoTemplate.remove(t).getDeletedCount();
    }

    public <T> T findById(String id, Class<T> cls) {
        return mongoTemplate.findById(id, cls);
    }

    public <T> List<T> findAll(Class<T> cls) {
        return mongoTemplate.findAll(cls);
    }

    public <T> Long count(Class<T> cls) {
        return mongoTemplate.count(new Query(), cls);
    }

    public <T> List<T> findByQuery(Query query, Class<T> cls) {
        return mongoTemplate.find(query, cls);
    }
}
