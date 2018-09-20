package com.ymatin.samlib.dao.author;

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
public class AuthorDaoImpl implements AuthorDao {

    private static final String SELECT_AUTHOR_BY_ID = "SELECT * FROM authors WHERE author_ID = :authorId";
    private static final String SELECT_AUTHOR_BY_AUTHOR_ID = "SELECT * FROM authors WHERE samlib_ID = :samlibId";
    private static final String INSERT_AUTHOR = "INSERT INTO authors " +
            "(author_ID, first_name, last_name, father_name, short_name, pseudonym, samlib_ID) " +
            "VALUES (:authorId, :firstName, :lastName, :fatherName, :shortName, :pseudonym, :samlibId)";
    public static final String SELECT_ALL = "SELECT * FROM authors";
    public static final String DELETE_AUTHOR_BY_ID = "DELETE FROM authors WHERE author_ID = :authorId";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AuthorDto findAuthorById(Long authorId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("authorId", authorId);
        return jdbcTemplate
                .queryForObject(SELECT_AUTHOR_BY_ID,
                        paramMap,
                        (rs, rowNum) -> fullAuthorDtoMapper(rs));
    }

    @Override
    public AuthorDto findAuthorBySamlibId(String samlibId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("samlibId", samlibId);
        return jdbcTemplate
                .queryForObject(SELECT_AUTHOR_BY_AUTHOR_ID,
                        paramMap,
                        (rs, rowNum) -> fullAuthorDtoMapper(rs));
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> fullAuthorDtoMapper(rs));
    }

    @Override
    public Long addAuthor(AuthorDto dto) {
        Long authorIdFromDB = null;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", dto.getAuthorId());
        params.addValue("firstName", dto.getFirstName());
        params.addValue("lastName", dto.getLastName());
        params.addValue("fatherName", dto.getFatherName());
        params.addValue("shortName", dto.getShortName());
        params.addValue("pseudonym", dto.getPseudonym());
        params.addValue("samlibId", dto.getSamlibId());
        int changedRows = jdbcTemplate.update(INSERT_AUTHOR, params, keyHolder, new String[]{"author_ID"});
        if (changedRows > 0) {
            authorIdFromDB = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
        return authorIdFromDB;
    }

    @Override
    public void deleteAuthor(Long authorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", authorId);
        jdbcTemplate.update(DELETE_AUTHOR_BY_ID, params);
    }

    @Override
    public void updateAuthor(AuthorDto dto) {
    }

    private AuthorDto fullAuthorDtoMapper(ResultSet rs) throws SQLException {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorId(rs.getLong("author_ID"));
        authorDto.setFirstName(rs.getString("first_name"));
        authorDto.setLastName(rs.getString("last_name"));
        authorDto.setFatherName(rs.getString("father_name"));
        authorDto.setShortName(rs.getString("short_name"));
        authorDto.setPseudonym(rs.getString("pseudonym"));
        authorDto.setSamlibId(rs.getString("samlib_ID"));
        return authorDto;
    }
}
