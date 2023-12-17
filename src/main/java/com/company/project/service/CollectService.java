package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Collect;

import java.util.List;

public interface CollectService extends Service<Collect> {

    Result list(Collect collect);

    Result addOrCancel(Collect collect);

    List<Collect> selectByUserId(Long id);

    Integer getCollectNumber(Long userId);

    Integer getCountByMomentId(Long momentId);

    void deleteByMomentId(Long momentId);

    Integer getCollectState(Long id, String userId);
}
