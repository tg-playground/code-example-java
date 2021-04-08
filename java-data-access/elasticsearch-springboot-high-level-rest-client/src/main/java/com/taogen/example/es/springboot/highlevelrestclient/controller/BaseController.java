package com.taogen.example.es.springboot.highlevelrestclient.controller;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Taogen
 */
public abstract class BaseController {
    @Autowired
    protected RestHighLevelClient restHighLevelClient;
}
