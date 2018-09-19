package com.ymatin.samlib.dao.author;

import java.util.List;

public interface AuthorInfoDao {
    AuthorInfoDto findAuthorInfoByInfoId(Long infoId);
    AuthorInfoDto findAuthorInfoByAuthorId(Long infoId);
    List<AuthorInfoDto> findAllInfos();
    Long addAuthorInfo(AuthorInfoDto dto);
    void deleteAuthorInfoByInfoId(Long authorInfoId);
    void deleteAuthorInfoByAuthorId(Long authorInfoId);
    void updateAuthorInfo(AuthorInfoDto dto);
}
