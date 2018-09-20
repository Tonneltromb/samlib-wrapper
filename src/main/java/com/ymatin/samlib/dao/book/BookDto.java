package com.ymatin.samlib.dao.book;

public class BookDto{

    private Long bookId;
    private String title;
    private String samlibRef;
    private Integer size;

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

    public String getSamlibRef() {
        return samlibRef;
    }

    public void setSamlibRef(String samlibRef) {
        this.samlibRef = samlibRef;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", samlibRef='" + samlibRef + '\'' +
                ", size=" + size +
                '}';
    }
}