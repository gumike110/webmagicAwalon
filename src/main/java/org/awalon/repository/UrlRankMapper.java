package org.awalon.repository;

import org.awalon.model.UrlRank;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UrlRank record);

    int insertSelective(UrlRank record);

    UrlRank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UrlRank record);

    int updateByPrimaryKey(UrlRank record);
}