package com.ymatin.samlib.dao.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE book_ID = :bookId";
    public static final String INSERT_BOOK = "INSERT INTO books (author_ID, title, samlib_ref, size) VALUES (:authorId, :title, :samlibRef, :size)";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books";
    private static final String INSERT_CHAPTER = "INSERT INTO chapters (book_ID, part_number, title, content) VALUES (:bookId, :partNumber, :title, :content)";

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
                        (rs, rowNum) -> bookDtoMapper(rs));
    }

    @Override
    public List<BookDto> getBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS, (rs, rowNum) -> bookDtoMapper(rs) );
    }

    @Override
    public Long insertBook(BookDto dto) {
        Long bookId = null;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource p = new MapSqlParameterSource();
        p.addValue("title", dto.getTitle());
        p.addValue("authorId", dto.getAuthorId());
        p.addValue("samlibRef", dto.getSamlibRef());
        p.addValue("size", dto.getSize());
        int updatedRows = jdbcTemplate.update(INSERT_BOOK, p, keyHolder, new String[]{"book_ID"});
        if (updatedRows > 0) {
            bookId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
        return bookId;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public void updateBook(BookDto dto) {

    }

    @Override
    public void insertChapter(ChapterDto dto) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", dto.getBookId());
        params.put("partNumber", dto.getPartNumber());
        params.put("title", dto.getTitle());
        params.put("content", dto.getContent());
        jdbcTemplate.update(INSERT_CHAPTER, params);
    }

    private BookDto bookDtoMapper(ResultSet rs) throws SQLException {
        BookDto dto = new BookDto();
        dto.setBookId(rs.getLong("book_ID"));
        dto.setAuthorId(rs.getLong("author_ID"));
        dto.setTitle(rs.getString("title"));
        dto.setSamlibRef(rs.getString("samlib_ref"));
        dto.setSize(rs.getInt("size"));
        return dto;
    }
}
