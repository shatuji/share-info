package me.twocat.shareinfo.service.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.twocat.shareinfo.entity.elasticsearchtest.ProfileDocument;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static javax.xml.crypto.dsig.XMLObject.TYPE;
import static org.elasticsearch.action.update.UpdateHelper.ContextFields.INDEX;

@Service
public class ProfileService {


  @Autowired
  private RestHighLevelClient client;

  @Autowired
  private ObjectMapper objectMapper;

  /***
   * create index about elactisearch
   * @param document
   * @return
   * @throws Exception
   */
  public String createProfileDocument(ProfileDocument document) throws Exception {

    UUID uuid = UUID.randomUUID();
   // document.setId(uuid.toString());
    CreateIndexRequest request = new CreateIndexRequest("test");
    request.settings(Settings.builder() .put("index.number_of_shards", 1)
      .put("index.number_of_replicas", 2));
    Map<String, Object> message = new HashMap<>();
    message.put("type", "text145454545as4df4as5df4a5sdf4a5sd4f5asdf45asdf4a5dsf4a5dsf45asdfa");
    Map<String, Object> properties = new HashMap<>();
    properties.put("message", message);
    properties.put("userId", message);
    properties.put("name", message);
    Map<String, Object> mapping = new HashMap<>();
    mapping.put("properties", properties);
    request.mapping(mapping);
    CreateIndexResponse indexResponse = client.indices()
                          .create(request , RequestOptions.DEFAULT);

    return indexResponse.index();
  }

  public String insertIndexDocument(Object obj) throws IOException {
    UUID uuid = UUID.randomUUID();
    IndexRequest indexRequest = new IndexRequest("test");
    indexRequest.id(uuid.toString());
    Map<String, Object> properties = new HashMap<>();
    String message = "this is elactisearch value below and you can search it ";
    properties.put("message", message);
    properties.put("userId", message);
    properties.put("name", message);
    indexRequest.source(properties , XContentType.JSON);
    IndexResponse indexResponse = client.index(indexRequest , RequestOptions.DEFAULT);
    System.out.println("print data about value id--->" + indexResponse.getId());
    return indexResponse.getResult().name();

  }







  public SearchResponse findAll(String field) throws Exception {

    SearchRequest searchRequest = new SearchRequest();
    QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name" , field);
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(matchQueryBuilder);
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse = client.search(searchRequest , RequestOptions.DEFAULT);

    return searchResponse;
  }






  private Map<String, Object> convertProfileDocumentToMap(ProfileDocument profileDocument) {
    return objectMapper.convertValue(profileDocument, Map.class);
  }

  private ProfileDocument convertMapToProfileDocument(Map<String, Object> map){
    return objectMapper.convertValue(map,ProfileDocument.class);
  }




}
