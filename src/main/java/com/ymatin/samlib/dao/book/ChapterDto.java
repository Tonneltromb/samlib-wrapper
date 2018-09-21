package com.ymatin.samlib.dao.book;

public class ChapterDto {
    private Long chapterId;
    private Long bookId;
    private Long partNumber;
    private String title;
    private String content;

    public ChapterDto() {}

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChapterDto{" +
                "chapterId=" + chapterId +
                ", bookId=" + bookId +
                ", partNumber=" + partNumber +
                ", title='" + title + '\'' +
                ", getBookContent='" + content + '\'' +
                '}';
    }
}
