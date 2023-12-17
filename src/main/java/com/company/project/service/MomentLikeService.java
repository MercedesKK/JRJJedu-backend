package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.MomentLike;

public interface MomentLikeService extends Service<MomentLike> {

    Result addOrCancel(MomentLike momentLike);

    Integer getCountBySubjectId(Long subjectId);

    Integer getCountStateBySubjectId(String userId, Long subjectId);

    Result list(MomentLike momentLike);
}
