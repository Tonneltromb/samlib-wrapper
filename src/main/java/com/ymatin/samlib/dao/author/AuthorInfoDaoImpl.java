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
public class AuthorInfoDaoImpl implements AuthorInfoDao {

    private static final String SELECT_AUTHOR_INFO_BY_INFO_ID = "SELECT * FROM author_info WHERE info_ID = :infoId";
    private static final String SELECT_AUTHOR_INFO_BY_AUTHOR_ID = "SELECT * FROM author_info WHERE author_ID = :authorId";
    private static final String INSERT_AUTHOR_INFO = "INSERT INTO author_info " +
            "(info_ID, author_ID, about_author, day_of_birth, month_of_birth, year_of_birth, email, web_site) " +
            "VALUES (:infoId, :authorId, :aboutAuthor, :dayOfBirth, :monthOfBirth, :yearOfBirth, :email, :webSite)";
    public static final String SELECT_ALL = "SELECT * FROM author_info";
    public static final String DELETE_AUTHOR_INFO_BY_INFO_ID = "DELETE FROM author_info WHERE info_ID = :infoId";
    public static final String DELETE_AUTHOR_INFO_BY_AUTHOR_ID = "DELETE FROM author_info WHERE author_ID = :authorId";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AuthorInfoDto findAuthorInfoByInfoId(Long infoId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("infoId", infoId);
        return jdbcTemplate
                .queryForObject(SELECT_AUTHOR_INFO_BY_INFO_ID,
                        paramMap,
                        (rs, rowNum) -> fullAuthorInfoDtoMapper(rs));
    }

    @Override
    public AuthorInfoDto findAuthorInfoByAuthorId(Long authorId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("authorId", authorId);
        return jdbcTemplate
                .queryForObject(SELECT_AUTHOR_INFO_BY_AUTHOR_ID,
                        paramMap,
                        (rs, rowNum) -> fullAuthorInfoDtoMapper(rs));
    }

    @Override
    public List<AuthorInfoDto> findAllInfos() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> fullAuthorInfoDtoMapper(rs));
    }

    @Override
    public Long addAuthorInfo(AuthorInfoDto dto) {
        Long infoId = null;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("infoId", dto.getInfoId());
        params.addValue("authorId", dto.getAuthorId());
        params.addValue("aboutAuthor", dto.getAboutAuthor());
        params.addValue("dayOfBirth", dto.getDayOfBirth());
        params.addValue("monthOfBirth", dto.getMonthOfBirth());
        params.addValue("yearOfBirth", dto.getYearOfBirth());
        params.addValue("email", dto.getEmail());
        params.addValue("webSite", dto.getWebSite());
        int changedRows = jdbcTemplate.update(INSERT_AUTHOR_INFO, params, keyHolder, new String[]{"info_ID"});
        if (changedRows > 0) {
            infoId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
        return infoId;
    }

    @Override
    public void deleteAuthorInfoByInfoId(Long infoId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("infoId", infoId);
        jdbcTemplate.update(DELETE_AUTHOR_INFO_BY_INFO_ID, params);
    }

    @Override
    public void deleteAuthorInfoByAuthorId(Long authorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", authorId);
        jdbcTemplate.update(DELETE_AUTHOR_INFO_BY_AUTHOR_ID, params);
    }

    @Override
    public void updateAuthorInfo(AuthorInfoDto dto) {

    }

    private AuthorInfoDto fullAuthorInfoDtoMapper(ResultSet rs) throws SQLException {
        AuthorInfoDto dto = new AuthorInfoDto();
        dto.setInfoId(rs.getLong("info_ID"));
        dto.setAuthorId(rs.getLong("author_ID"));
        dto.setAboutAuthor(rs.getString("about_author"));
        dto.setDayOfBirth(rs.getInt("day_of_birth"));
        dto.setMonthOfBirth(rs.getInt("month_of_birth"));
        dto.setYearOfBirth(rs.getInt("year_of_birth"));
        dto.setEmail(rs.getString("email"));
        dto.setWebSite(rs.getString("web_site"));
        return dto;
    }
}
