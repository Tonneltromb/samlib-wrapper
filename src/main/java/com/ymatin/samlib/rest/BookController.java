package com.ymatin.samlib.rest;

import com.ymatin.samlib.model.Book;
import com.ymatin.samlib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/get")
    public String getBook(Model model) {
        Book book = bookService.getBook(1L);
        model.addAttribute("book", book);
        return "book";
    }
}
