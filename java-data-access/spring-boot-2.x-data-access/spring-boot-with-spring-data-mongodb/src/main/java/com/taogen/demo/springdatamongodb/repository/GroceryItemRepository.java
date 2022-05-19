package com.taogen.demo.springdatamongodb.repository;

import com.taogen.demo.springdatamongodb.entity.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Taogen
 */
public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

    @Query("{name:'?0'}")
    List<GroceryItem> findAllByNameCustom(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1}")
    List<GroceryItem> findAllByCategoryCustom(String category);

    List<GroceryItem> findAllByQuantity(int i);
}
