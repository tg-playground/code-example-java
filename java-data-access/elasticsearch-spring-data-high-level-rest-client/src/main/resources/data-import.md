# Data Import

## Indexing documents in bulk

Command lines

```
curl -H "Content-Type: application/json" -XPOST "localhost:9200/bank/_bulk?pretty&refresh" --data-binary "@accounts.json"
curl "localhost:9200/_cat/indices?v=true"
```

Kibana Dev Tools

```
POST /bank/_doc/_bulk
{"index":{"_id":"1"}}
{"account_number":1,"balance":39225,"firstname":"Amber","lastname":"Duke","age":32,"gender":"M","address":"880 Holmes Lane","employer":"Pyrami","email":"amberduke@pyrami.com","city":"Brogan","state":"IL"}
...
```

Data URL: https://raw.githubusercontent.com/elastic/elasticsearch/master/docs/src/test/resources/accounts.json
