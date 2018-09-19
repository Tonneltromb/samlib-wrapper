package com.ymatin.samlib.service;

import com.ymatin.samlib.dao.author.AuthorDao;
import com.ymatin.samlib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author getAuthor(Long authorId) {
        return null;
    }

    @Override
    public List<Author> getAuthors() {
        return null;
    }

    @Override
    public Long addAuthor(Author dto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId) {

    }

    @Override
    public void updateAuthor(Author dto) {

    }
}
