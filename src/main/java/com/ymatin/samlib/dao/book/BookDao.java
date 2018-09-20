package com.ymatin.samlib.dao.book;

import java.util.List;

public interface BookDao {
    BookDto getBook(Long id);
    List<BookDto> getBooks();
    Long addBook(BookDto dto);
    void deleteBook(Long id);
    void updateBook(BookDto dto);
 }
