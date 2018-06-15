package com.manning;

/**
 * Created by hanyu on 2018/6/12.
 */
public class Paint {
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Paint{" +
                "author='" + author + '\'' +
                '}';
    }
}
