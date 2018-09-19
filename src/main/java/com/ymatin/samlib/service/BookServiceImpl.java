package com.ymatin.samlib.service;

import com.ymatin.samlib.dao.book.BookDao;
import com.ymatin.samlib.dao.book.BookDto;
import com.ymatin.samlib.model.Book;
import com.ymatin.samlib.model.SingleAuthorBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBook(Long id) {
        Book book = new SingleAuthorBook();
        BookDto bookEntity = bookDao.getBook(id);
        book.setContent(bookEntity.getTitle());
        return book;
    }
}
