package com.taogen.demo.springdatamongodb.template;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;

/**
 * @author Taogen
 */
public class MongoTemplateHelper {
    public static Criteria getAndConditions(Collection<Criteria> criteriaList) {
        return new Criteria().andOperator(criteriaList);
    }

    public static Criteria getOrConditions(Collection<Criteria> criteriaList) {
        return new Criteria().orOperator(criteriaList);
    }
}
