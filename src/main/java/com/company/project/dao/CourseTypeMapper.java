package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.CourseType;

import java.util.List;

public interface CourseTypeMapper extends Mapper<CourseType> {

    List<CourseType> selectByGoodsId(Long goodsId);

}