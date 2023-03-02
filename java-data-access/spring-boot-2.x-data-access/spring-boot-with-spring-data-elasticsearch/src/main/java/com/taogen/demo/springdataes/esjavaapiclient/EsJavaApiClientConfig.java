package com.taogen.demo.springdataes.esjavaapiclient;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
public class EsJavaApiClientConfig {
    @Bean
    public RestClient restClient() {
        return RestClient.builder(
                new HttpHost("localhost", 9200)).build();
    }
}
