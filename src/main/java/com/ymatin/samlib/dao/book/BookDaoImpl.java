package com.ymatin.samlib.dao.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookDto getBook(Long bookId) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("bookId", bookId);
        return jdbcTemplate
                .queryForObject(SELECT_BOOK_BY_ID,
                        paramMap,
                        (rs, rowNum) -> {
                            BookDto bookDto = new BookDto();
                            bookDto.setBookId(rs.getLong("book_ID"));
                            bookDto.setTitle(rs.getString("title"));
                            return bookDto;
                        });
    }

    @Override
    public List<BookDto> getBooks() {
        return null;
    }

    @Override
    public void addBook(BookDto dto) {

    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public void updateBook(BookDto dto) {

    }
}
