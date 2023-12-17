package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.MomentComment;

import java.util.List;

public interface MomentCommentService extends Service<MomentComment> {

    Result add(MomentComment momentComment);

    List<MomentComment> selectByMomentId(Long momentId);

    Result detail(Long momentId);

    Result list(MomentComment momentComment);

    Integer getCountByMomentId(Long momentId);
}
