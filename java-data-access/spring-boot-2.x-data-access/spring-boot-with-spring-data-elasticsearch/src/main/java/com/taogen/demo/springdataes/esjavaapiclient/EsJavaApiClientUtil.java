package com.taogen.demo.springdataes.esjavaapiclient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.taogen.demo.springdataes.entity.Bank;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @author Taogen
 */
@Component
public class EsJavaApiClientUtil {

    @Autowired
    RestClient restClient;

    private ElasticsearchClient client;

    @PostConstruct
    public void init() {
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
    }

    public boolean insert() {
        return false;
    }

    public boolean delete() {

        return false;
    }

    public boolean update() {

        return false;
    }

    public Object getById() {
        return null;
    }

    public List findPage() {
        SearchResponse<Bank> search = null;
        try {
            search = client.search(s -> s
                            .index("bank")
                            .query(q -> q
                                    .term(t -> t
                                            .field("name")
                                            .value(v -> v.stringValue("bicycle"))
                                    )),
                    Bank.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Hit<Bank> hit: search.hits().hits()) {
            System.out.println(hit.source());
        }
        return null;
    }

    public Long count() {
        return null;
    }

}
