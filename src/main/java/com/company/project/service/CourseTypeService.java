package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.CourseType;

import java.util.List;

public interface CourseTypeService extends Service<CourseType> {

    Result list(CourseType courseType);

    List<CourseType> selectByGoodsId(Long goodsId);
}
