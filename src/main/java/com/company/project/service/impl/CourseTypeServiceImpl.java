package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CourseTypeMapper;
import com.company.project.model.CourseType;
import com.company.project.service.CourseTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseTypeServiceImpl extends AbstractService<CourseType> implements CourseTypeService {

    @Resource
    private CourseTypeMapper courseTypeMapper;

    @Override
    public Result list(CourseType courseType) {

        if (null == courseType){
            courseType = new CourseType();
        }

        PageHelper.startPage(courseType.getPage() == null ? 0 : courseType.getPage(), courseType.getLimit() == null ? 10 : courseType.getLimit());
        courseType.setIsDelete(false);
        List<CourseType> list = findByModel(courseType);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public List<CourseType> selectByGoodsId(Long goodsId) {
        return courseTypeMapper.selectByGoodsId(goodsId);
    }
}
