package com.ymatin.samlib.dao.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorInfoDaoImpl implements AuthorInfoDao {

    private static final String SELECT_AUTHOR_INFO_BY_INFO_ID = "SELECT * FROM author_info WHERE info_ID = :infoId";
    private static final String SELECT_AUTHOR_INFO_BY_AUTHOR_ID = "SELECT * FROM author_info WHERE author_ID = :authorId";
    private static final String INSERT_AUTHOR_INFO = "INSERT INTO author_info " +
            "(info_ID, author_ID, about_author, day_of_birth, month_of_birth, year_of_birth, email, web_site) " +
            "VALUES (:infoId, :authorId, :aboutAuthor, :day, :month, :year, :email, :webSite)";
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
        paramMap.put("authorId", infoId);
        return jdbcTemplate
                .queryForObject(SELECT_AUTHOR_INFO_BY_INFO_ID,
                        paramMap,
                        (rs, rowNum) -> getFullMappedAuthorInfoDto(rs));
    }

    @Override
    public AuthorInfoDto findAuthorInfoByAuthorId(Long infoId) {
        return null;
    }

    @Override
    public List<AuthorInfoDto> findAllInfos() {
        return null;
    }

    @Override
    public Long addAuthorInfo(AuthorInfoDto dto) {
        return null;
    }

    @Override
    public void deleteAuthorInfoByInfoId(Long authorInfoId) {

    }

    @Override
    public void deleteAuthorInfoByAuthorId(Long authorInfoId) {

    }

    @Override
    public void updateAuthorInfo(AuthorInfoDto dto) {

    }

    private AuthorInfoDto getFullMappedAuthorInfoDto(ResultSet rs) throws SQLException {
        AuthorInfoDto dto = new AuthorInfoDto();
        dto.setInfoId(rs.getLong("info_ID"));
        dto.setAuthorId(rs.getLong("author_ID"));
        dto.setAboutAuthor(rs.getString("about_author"));
        dto.setDayOfBirth(rs.getInt("day_of_birth"));
        dto.setMonthOfBirth(rs.getInt("month_of_birth"));
        dto.setYearOfBirth(rs.getInt("year_of_birth"));
        dto.setEmail(rs.getString("email"));
        dto.setWebSite(rs.getString("web_site"));
        return null;
    }
}
