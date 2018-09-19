package com.ymatin.samlib.dao.author;

import java.util.List;

public interface AuthorDao {
    AuthorDto findAuthorById(Long authorId);
    List<AuthorDto> findAllAuthors();
    Long addAuthor(AuthorDto dto);
    void deleteAuthor(Long authorId);
    void updateAuthor(AuthorDto dto);
}
