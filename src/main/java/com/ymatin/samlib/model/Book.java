package com.ymatin.samlib.model;

import java.util.Map;

public abstract class Book {
    private String title;
    private String genre;
    // TODO: 15.08.2018 контент в разработке BookInfo bookInfo;
    private Map<Integer, Chapter>  chapters;
    private byte[] cover;
    private Integer size;
    private String content;
    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
