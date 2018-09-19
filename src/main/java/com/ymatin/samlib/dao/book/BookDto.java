package com.ymatin.samlib.dao.book;

public class BookDto{

    private Long bookId;
    private String title;

    public BookDto() {}

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}