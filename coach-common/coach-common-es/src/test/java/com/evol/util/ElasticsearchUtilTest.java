package com.evol.util;

import com.evol.model.TestDto;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchUtilTest {

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    private final String indexName = "test_index";

    @Test
    public void createIndexTest(){
        boolean result = elasticsearchUtil.createIndex(indexName);
        assert(result);
    }

    @Test
    public void deleteIndexTest(){
        boolean result = elasticsearchUtil.deleteIndex(indexName);
        assert(result);
    }

    @Test
    public void insertDocTest(){
        TestDto dto = new TestDto();
        dto.setId(1);
        dto.setName("testname1");
        dto.setRemark("remark1");
        dto.setCreateTime(new Date());
        boolean result = elasticsearchUtil.insertDoc(indexName, dto);
    }


    @Test
    public void deleteDocTest(){
        boolean result = elasticsearchUtil.deleteDoc(indexName, "11");
    }


    @Test
    public void batchInsertDocTest(){
        List<TestDto> list = new ArrayList<>();
        TestDto dto1 = new TestDto();
        TestDto dto2 = new TestDto();
        dto1.setId(11);
        dto1.setName("testname11");
        dto1.setRemark("remark11");
        dto1.setCreateTime(new Date());
        dto2.setId(12);
        dto2.setName("testname12");
        dto2.setRemark("remark12");
        dto2.setCreateTime(new Date());

        list.add(dto1);
        list.add(dto2);

        boolean result = elasticsearchUtil.batchInsertDoc(indexName, list, (e) -> e.getId().toString());
    }


    @Test
    public void searchDocTest(){
        String name = "testname11";
        String outId = null;
        Integer from = 0;
        Integer size = 100;
        List<TestDto> result = elasticsearchUtil.search(indexName, TestDto.class, (sourceBuilder) -> {
            //精确查询
            if(StringUtils.isNotEmpty(name)){
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", name);//精准查询
                sourceBuilder.query(termQueryBuilder);
            }

            if(outId != null){
                TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("outId", outId);//精准查询
                sourceBuilder.query(termQueryBuilder2);
            }

            FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("createTime");//按照年龄排序
            fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序

            sourceBuilder.sort(fieldSortBuilder);//多条件查询
            sourceBuilder.from(from);
            sourceBuilder.size(size);
            return sourceBuilder;
        });

    }

    @Test
    public void exportDocTest(){
        String name = "testname11";
        String outId = null;
        Integer from = 1;
        Integer size = 1000;
        List<TestDto> result = elasticsearchUtil.export(indexName, TestDto.class, 200, (sourceBuilder) -> {

//
            //精确查询
            if(StringUtils.isNotEmpty(name)){
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", name);//精准查询
                sourceBuilder.query(termQueryBuilder);
            }

            if(outId != null){
                TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("outId", outId);//精准查询
                sourceBuilder.query(termQueryBuilder2);
            }

            FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("createTime");//按照年龄排序
            fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序

            sourceBuilder.sort(fieldSortBuilder);//多条件查询

            return sourceBuilder;


        });

    }

}
