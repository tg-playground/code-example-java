package com.taogen.example.springbootwithes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.sql.Date;

/**
 * @author Taogen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "rdbms_sync_idx")
public class EsTable {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, name = "client_name")
    private String clientName;

    @Field(type = FieldType.Text, name = "modification_time")
    private String modificationTime;

    @Field(type = FieldType.Text, name = "insertion_time")
    private String insertionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(String insertionTime) {
        this.insertionTime = insertionTime;
    }
}
