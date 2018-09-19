package com.ymatin.samlib.service;

import com.ymatin.samlib.dao.author.AuthorDto;
import com.ymatin.samlib.model.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthor(Long authorId);
    List<Author> getAuthors();
    Long addAuthor(Author dto);
    void deleteAuthor(Long authorId);
    void updateAuthor(Author dto);
}
