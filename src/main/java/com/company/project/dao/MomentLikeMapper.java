package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.MomentLike;
import org.apache.ibatis.annotations.Param;

public interface MomentLikeMapper extends Mapper<MomentLike> {

    MomentLike selectByIdAndIsDelete(@Param("subjectId") Long likeId, @Param("createdBy") String createdBy, @Param("status") Integer status);

    int cancelMomentLike(MomentLike momentLike);

    int findUserMomentLikeById(MomentLike momentLike);

    Integer getCountBySubjectId(Long subjectId);

    Integer getCountStateBySubjectId(@Param("userId") String userId,@Param("subjectId") Long subjectId);
}
