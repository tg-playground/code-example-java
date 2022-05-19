package com.taogen.demo.springdatamongodb.repository;

import com.taogen.demo.springdatamongodb.entity.GroceryItem;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GroceryItemRepositoryTest {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Test
    void save() {
        ObjectId id = new ObjectId();
        GroceryItem entity = insertRandomEntityById(id);
        assertNotNull(entity);
        assertEquals(id.toString(), entity.getId());
    }

    public GroceryItem insertRandomEntityById(ObjectId objectId) {
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return groceryItemRepository.save(new GroceryItem(
                objectId.toString(), "Whole Wheat Biscuit - " + datetime, 5, "snacks"));
    }

    @Test
    void delete() {
        ObjectId id = new ObjectId();
        GroceryItem insertEntity = insertRandomEntityById(id);
        assertNotNull(insertEntity);
        assertEquals(id.toString(), insertEntity.getId());
        groceryItemRepository.deleteById(id.toString());
        Optional<GroceryItem> checkEntity = groceryItemRepository.findById(id.toString());
        assertFalse(checkEntity.isPresent());
    }

    @Test
    void update() {
        ObjectId id = new ObjectId();
        GroceryItem insertEntity = insertRandomEntityById(id);
        assertNotNull(insertEntity);
        assertEquals(id.toString(), insertEntity.getId());
        String newName = "Whole Wheat Biscuit 222";
        GroceryItem updateEntity = groceryItemRepository.save(new GroceryItem(id.toString(), newName, 5, "snacks"));
        assertNotNull(updateEntity);
        assertEquals(id.toString(), updateEntity.getId());
        assertEquals(newName, updateEntity.getName());
    }

    @Test
    void findById() {
        ObjectId id = new ObjectId();
        GroceryItem insertEntity = insertRandomEntityById(id);
        assertNotNull(insertEntity);
        assertEquals(id.toString(), insertEntity.getId());
        Optional<GroceryItem> checkEntity = groceryItemRepository.findById(id.toString());
        assertTrue(checkEntity.isPresent());
        assertEquals(id.toString(), checkEntity.get().getId());
    }

    @Test
    void findAllByName() {
        ObjectId id = new ObjectId();
        GroceryItem insertEntity = insertRandomEntityById(id);
        assertNotNull(insertEntity);
        assertEquals(id.toString(), insertEntity.getId());
        List<GroceryItem> checkEntity = groceryItemRepository.findAllByNameCustom("Whole Wheat Biscuit");
        System.out.println(checkEntity);
        assertNotNull(checkEntity);
    }

    @Test
    void findAllByCategory() {
        List<GroceryItem> snacks = groceryItemRepository.findAllByCategoryCustom("snacks");
        System.out.println(snacks);
    }

    @Test
    void findAllByQuantity() {
        List<GroceryItem> quantity = groceryItemRepository.findAllByQuantity(5);
        System.out.println(quantity);
    }

    @Test
    void count() {
        long count = groceryItemRepository.count();
        System.out.println(count);

    }
}
