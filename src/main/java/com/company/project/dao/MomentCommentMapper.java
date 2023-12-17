package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.MomentComment;

import java.util.List;

public interface MomentCommentMapper extends Mapper<MomentComment> {

    List<MomentComment> selectByMomentId(Long momentId);

    List<MomentComment> selectParentByMomentId(Long momentId);

    List<MomentComment> detail(Long momentId);

    List<MomentComment> list(MomentComment momentComment);

    Integer getCountByMomentId(Long momentId);

    List<MomentComment> selectChildrenByParentId(Long parentId);
}
