package com.ymatin.samlib.controller;

import com.ymatin.samlib.dao.author.AuthorDao;
import com.ymatin.samlib.dao.author.AuthorDto;
import com.ymatin.samlib.dao.author.AuthorInfoDao;
import com.ymatin.samlib.dao.author.AuthorInfoDto;
import com.ymatin.samlib.dao.book.BookDao;
import com.ymatin.samlib.dao.book.BookDto;
import com.ymatin.samlib.dao.book.ChapterDto;
import com.ymatin.samlib.service.FillDatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/filldb")
public class FillDatabaseFromSamlibController {

    private FillDatabaseHelper dbHelper;
    private AuthorDao authorDao;
    private AuthorInfoDao authorInfoDao;
    private BookDao bookDao;

    @Autowired
    public void setDbHelper(FillDatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    public void setAuthorInfoDao(AuthorInfoDao authorInfoDao) {
        this.authorInfoDao = authorInfoDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("findAll")
    public String getAllAuthors(Model model) {
        List<AuthorDto> authors = authorDao.findAllAuthors();
        model.addAttribute("authors", authors);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/findAuthor")
    public String addAuthor(@RequestParam("authorId") Long authorId, Model model) {
        AuthorDto dto = authorDao.findAuthorById(authorId);
        AuthorInfoDto infoDto = authorInfoDao.findAuthorInfoByAuthorId(authorId);
        model.addAttribute("author", dto);
        model.addAttribute("info", infoDto);
        return "filldb/filldb-welcome-page";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(
            @RequestParam("pageRef") String pageRef) {
        dbHelper.addAuthorByPageReference(pageRef);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/delete")
    public String deleteAuthor(
            @RequestParam("authorId") Long authorId) {
        authorDao.deleteAuthor(authorId);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/getAllInfos")
    public String getAllInfos(Model model) {
        List<AuthorInfoDto> allInfos = authorInfoDao.findAllInfos();
        model.addAttribute("infos", allInfos);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/allBooks")
    public String allBooks(Model model) {
        List<BookDto> books = bookDao.getBooks();
        model.addAttribute("books", books);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/searchBooks")
    public String searchBooks(@RequestParam("authorId") Long authorId) {
        dbHelper.searchAndInsertBooksBySamlibId(authorId, 100, 700);
        return "filldb/filldb-welcome-page";
    }

    @GetMapping("/showBooks")
    public String content(@RequestParam("authorId") Long authorId, Model model) {
        return "filldb/filldb-welcome-page";
    }
}
