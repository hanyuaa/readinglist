package com.manning;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hanyu on 2018/4/17.
 */
public class Demo_04 {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.setId(1l);
        b1.setTitle("2017-5");
        Book b2 = new Book();
        b2.setId(2l);
        b2.setTitle("2017-6");
        Book b3 = new Book();
        b3.setId(3l);
        b3.setTitle("2017-2");
        Book b4 = new Book();
        b4.setId(4l);
        b4.setTitle("2017-4");
        List<Book> books = Lists.newArrayList();
        books = books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());

        System.out.println(books);
    }

}
