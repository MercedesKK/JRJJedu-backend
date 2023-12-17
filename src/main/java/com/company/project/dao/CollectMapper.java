package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper extends Mapper<Collect> {

    int findCollectCount(@Param("createdBy") String createdBy, @Param("momentId") Long momentId);

    void cancel(Collect collect);

    List<Collect> selectByUserId(Long userId);

    Integer getCollectNumber(Long userId);

    Integer getCountByMomentId(Long momentId);

    List<Collect> list(Collect collect);

    void deleteByMomentId(Long momentId);

    Integer getCollectState(@Param("id") Long id, @Param("userId") String userId);
}