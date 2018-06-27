package com.manning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

/**
 *  Elasticsearch 测试
 */
public class ES_Demo {

    @Autowired
    private static ElasticsearchTemplate elasticsearchTemplate;

    public static void main(String[] args) {
        Book book = new Book();
        book.setId(1l);
        book.setAuthor("张三");

        IndexQuery usertest = new IndexQueryBuilder().withIndexName("usertest").withId("1").withObject(book).build();
        String index = ES_Demo.elasticsearchTemplate.index(usertest);
        System.out.println(index);
    }
}
