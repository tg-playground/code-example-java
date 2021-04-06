package com.taogen.example.es.basic.entity;

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
    private Integer id;

    @Field(name = "account_number")
    private Long accountNumber;

    @Field(type = FieldType.Keyword)
    private String address;

    private Integer age;

    private Long balance;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Keyword)
    private String employer;

    @Field(name = "firstname", type = FieldType.Keyword)
    private String firstName;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(name = "lastname", type = FieldType.Keyword)
    private String lastName;

    @Field(type = FieldType.Keyword)
    private String state;
}
