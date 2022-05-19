package com.taogen.demo.springdatamongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * If the document is not exists, it will be created automatically
 *
 * @author Taogen
 */
@Document("groceryitems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItem {
    @Id
    private String id;

    private String name;
    private Integer quantity;
    private String category;

    public GroceryItem(String id) {
        this.id = id;
    }
}
