package com.evol.util;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
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
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchUtilTest {

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    @Test
    public void createIndexTest(){
        String index = "test_index_1";
        boolean result = elasticsearchUtil.createIndex(index);
    }

    @Test
    public void insertIndexTest(){
        String index = "test_index_1";
        TestDto dto = new TestDto();
        dto.setId(1);
        dto.setName("testname1");
        boolean result = elasticsearchUtil.insertIndex(index, dto);
    }


    @Test
    public void deleteIndexTest(){
        String index = "test_index_1";
        boolean result = elasticsearchUtil.deleteIndex(index, "1");
    }


    @Test
    public void batchInsertTest(){
        String index = "test_index_1";
        List<TestDto> list = new ArrayList<>();
        TestDto dto1 = new TestDto();
        TestDto dto2 = new TestDto();
        dto1.setId(11);
        dto1.setName("testname11");
        dto2.setId(12);
        dto2.setName("testname12");

        list.add(dto1);
        list.add(dto2);

        boolean result = elasticsearchUtil.batchInsert(index, list, (e) -> e.getId().toString());
    }


    @Test
    public void searchIndexTest(){
        String index = "test_index_1";
        String name = null;
        String outId = null;
        Integer from = 1;
        Integer size = 100;
        List<TestDto> result = elasticsearchUtil.search(index, TestDto.class, (sourceBuilder) -> {
            //精确查询
            if(StringUtils.isNotEmpty(name)){
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(name, name);//精准查询
                sourceBuilder.query(termQueryBuilder);
            }

            if(outId != null){
                TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery(outId, outId);//精准查询
                sourceBuilder.query(termQueryBuilder2);
            }



            FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("createTime");//按照年龄排序
            fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序


            //sourceBuilder.query(rangeQueryBuilder).query(termQueryBuilder).query(termQueryBuilder2).sort
            // (fieldSortBuilder);//多条件查询
            sourceBuilder.sort(fieldSortBuilder);//多条件查询
            sourceBuilder.from(from);
            sourceBuilder.size(size);
            return sourceBuilder;
        });

    }

    @Test
    public void exportTest(){
        String index = "test_index_1";
        String name = null;
        String outId = null;
        Integer from = 1;
        Integer size = 100;
        List<TestDto> result = elasticsearchUtil.export(index, TestDto.class, 200, (sourceBuilder) -> {
            //精确查询
            if(StringUtils.isNotEmpty(name)){
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(name, name);//精准查询
                sourceBuilder.query(termQueryBuilder);
            }

            if(outId != null){
                TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery(outId, outId);//精准查询
                sourceBuilder.query(termQueryBuilder2);
            }



            FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("createTime");//按照年龄排序
            fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序


            //sourceBuilder.query(rangeQueryBuilder).query(termQueryBuilder).query(termQueryBuilder2).sort
            // (fieldSortBuilder);//多条件查询
            sourceBuilder.sort(fieldSortBuilder);//多条件查询
            sourceBuilder.from(from);
            sourceBuilder.size(size);
            return sourceBuilder;
        });

    }


    @Data
    public class TestDto{

        private Integer id;

        private String name;

    }

}
