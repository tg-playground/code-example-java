package com.taogen.example.es.springboot.highlevelrestclient.controller.document;

import com.taogen.example.es.springboot.highlevelrestclient.controller.BaseController;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * Single Document APIs
 *
 * @author Taogen
 */
@RestController
@RequestMapping("/documents")
public class DocumentController extends BaseController {
    private static String INDEX_NAME = "bank";

    @PostMapping
    public ResponseEntity save(@RequestBody String json) throws IOException {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .source(json, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(indexResponse);
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity delete(@PathVariable("_id") String _id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME, _id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(deleteResponse);
    }

    @PutMapping("/{_id}")
    public ResponseEntity update(@PathVariable("_id") String _id,
                                 @RequestBody String json) throws IOException {

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id(_id).source(json, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(indexResponse);
    }

    @GetMapping("{_id}")
    public ResponseEntity get(@PathVariable("_id") String _id) throws IOException {
        GetRequest getRequest = new GetRequest(INDEX_NAME, _id);
        GetResponse getResponse = restHighLevelClient.get(getRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(getResponse);
    }

    @GetMapping
    public ResponseEntity multiGet(String[] ids) throws IOException {
        MultiGetRequest multiGetRequest = new MultiGetRequest();
        if (ids == null || ids.length == 0) {
            return ResponseEntity.noContent().build();
        }
        Arrays.asList(ids).forEach(item -> {
            multiGetRequest.add(INDEX_NAME, item);
        });
        MultiGetResponse multiGetItemResponses = restHighLevelClient.mget(multiGetRequest,
                RequestOptions.DEFAULT);
        return ResponseEntity.ok(multiGetItemResponses);
    }
}
