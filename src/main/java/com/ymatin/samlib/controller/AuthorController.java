package com.ymatin.samlib.controller;

import com.ymatin.samlib.dao.author.AuthorDao;
import com.ymatin.samlib.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
public class AuthorController {

    //    private AuthorServiceImpl authorService;
    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

//    @RequestMapping("/find{authorId}")
//    public String findAuthorInfoByInfoId(@RequestParam("authorId") Integer authorId) {
//        return
//    }
}
