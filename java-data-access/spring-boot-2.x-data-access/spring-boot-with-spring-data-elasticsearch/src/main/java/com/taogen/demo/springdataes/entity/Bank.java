package com.taogen.demo.springdataes.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Taogen
 */
@Data
@Document(indexName = "bank")
public class Bank {
    @Id
    private String id;

    private Long accountNumber;

    @Field(type = FieldType.Text)
    private String address;

    private Integer age;

    private Long balance;

    private String city;

    private String email;

    private String employer;

    private String firstName;

    private String gender;

    private String lastName;

    private String state;
}
