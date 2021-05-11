package com.evol.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author admin
 */
@Slf4j
@Component
public class ElasticsearchUtil {

    @Qualifier(value = "restHighLevelClient")
    @Autowired
    RestHighLevelClient client;

    /**
     * 创建索引
     * @param indexName
     * @return
     */
    public boolean createIndex(String indexName) {
        if(StringUtils.isEmpty(indexName)){return false;}
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        // 2、客户端client执行请求 IndicesClient对象,请求后获得响应
        try {
            CreateIndexResponse createIndexResponse =
                    client.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (IOException ex) {
            log.error("创建索引失败:" + indexName, ex);
            return false;
        }
    }

    /**
     * 删除索引
     * @param indexName
     * @return
     */
    public boolean deleteIndex(String indexName) {
        if(StringUtils.isEmpty(indexName)){return false;}
        // 1、创建索引请求
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        // 2、客户端client执行请求 IndicesClient对象,请求后获得响应
        try {
            AcknowledgedResponse acknowledgedResponse =
                    client.indices().delete(request, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (IOException ex) {
            log.error("删除索引失败:" + indexName, ex);
            return false;
        }
    }

    /**
     * 插入文档
     * @param indexName
     * @param item
     * @param <T>
     * @return
     */
    public <T> boolean insertDoc(String indexName, T item){
        if(StringUtils.isEmpty(indexName)){return false;}
        if(item == null){return false;}
        try{

            String json = JsonUtil.ParseString(item);
            IndexRequest indexRequest = new IndexRequest(indexName).source(json, XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            String id = indexResponse.getId();
            String index = indexResponse.getIndex();
            long version = indexResponse.getVersion();
            int status = indexResponse.status().getStatus();
            if(indexResponse.getResult() == DocWriteResponse.Result.CREATED
                    || indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
                return true;
            }
            return false;
        } catch (IOException ex) {
            log.error("插入文档失败:" + indexName, ex);
            return false;
        }
    }

    /**
     * 批量插入
     * @param indexName
     * @param items
     * @param idFunc
     * @param <T>
     * @return
     */
    public <T> boolean batchInsertDoc(String indexName, List<T> items, Function<T, String> idFunc){
        if(StringUtils.isEmpty(indexName)){return false;}
        if(items == null || items.size() == 0){return false;}
        BulkRequest bulkRequest = new BulkRequest();

        items.forEach(item -> {
            String json = JsonUtil.ParseString(item);
            IndexRequest indexRequest = new IndexRequest(indexName).id(idFunc.apply(item)).source(json,
                    XContentType.JSON);
            bulkRequest.add(indexRequest);
        });

        try {
            BulkResponse bulkResp = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if(bulkResp.hasFailures()){
                return false;
            }
        } catch (IOException ex) {
            log.error("批量插入文档失败", ex);
        }
        return true;
    }

    /**
     * 删除文档
     * @param indexName
     * @param id
     * @return
     */
    public boolean deleteDoc(String indexName, String id){
        if(StringUtils.isEmpty(indexName) || StringUtils.isEmpty(id)){return false;}
        DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
        try {
            DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
                return true;
            }
        } catch (IOException ex) {
            log.error("删除文档失败，index:" + indexName + ",id:" + id, ex);
        }
        return false;
    }

    /**
     * 搜索文档
     * @param indexName
     * @param builder
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> search(String indexName, Class<T> clazz, Function<SearchSourceBuilder, SearchSourceBuilder> builder){
        List<T> list = new ArrayList<>();
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder = builder.apply(sourceBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                T obj = JsonUtil.ParseObject(sourceAsString, clazz);
                list.add(obj);
            }
            return list;
        } catch (IOException ex) {
            log.error("查询文档失败", ex);
        }
        return list;
    }

    /**
     * ES scroll 导出，每次滚动9000条
     * @param indexName
     * @param builder
     * @param clazz
     * @param maxScrollNumber
     * @param <T>
     * @return
     */
    public <T> List<T> export(String indexName,
                              Class<T> clazz,
                              Integer maxScrollNumber, Function<SearchSourceBuilder, SearchSourceBuilder> builder){
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest(indexName);

        searchRequest.scroll(scroll);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder = builder.apply(sourceBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.from(0);
        sourceBuilder.size(9000);
        searchRequest.source(sourceBuilder);

        String scrollId = null;
        SearchHit[] hits = null;
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            hits = response.getHits().getHits();
            scrollId = response.getScrollId();
        } catch (IOException e) {
            log.error("查询异常", e);
        }

        List<SearchHit> hitList = new ArrayList<>();
        hitList.addAll(org.springframework.util.CollectionUtils.arrayToList(hits));
        Integer i = maxScrollNumber;

        while (hits != null && hits.length > 0 && i > 0){
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                SearchResponse response = client.scroll(scrollRequest, RequestOptions.DEFAULT);
                scrollId = response.getScrollId();
                hits = response.getHits().getHits();
                i--;
                hitList.addAll(CollectionUtils.arrayToList(hits));
            } catch (IOException e) {
                log.error("es scroll失败", e);
            }
        }

        try {
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            boolean succeeded = clearScrollResponse.isSucceeded();
        } catch (IOException e) {
            log.error("clear scroll 失败", e);
        }

        List<T> list = new ArrayList<>();
        for (SearchHit hit : hitList) {
            String sourceAsString = hit.getSourceAsString();
            T vo = JsonUtil.ParseObject(sourceAsString, clazz);
            list.add(vo);
        }
        return list;
    }

}
