package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CourseMapper;
import com.company.project.model.Course;
import com.company.project.service.CourseChapterService;
import com.company.project.service.CourseService;
import com.company.project.service.MomentCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends AbstractService<Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MomentCommentService momentCommentService;

    @Resource
    private CourseChapterService courseChapterService;

    @Override
    public List<Course> selectByCourseTypeId(Long courseTypeId) {
        return courseMapper.selectByCourseTypeId(courseTypeId);
    }

    @Override
    public Result detail(Long id) {

        if (null == id){
            return ResultGenerator.genSuccessResult(new Course());
        }else {
            Course d = courseMapper.detail(id);
            if (null == d){
                return ResultGenerator.genSuccessResult(new Course());
            }else {
                if (null != d.getViews()){
                    d.setViews(d.getViews() + 1);
                }else {
                    d.setViews(1);
                }
                update(d);

                d.setMomentCommentList(momentCommentService.selectByMomentId(d.getId()));
                d.setCourseChapterList(courseChapterService.selectByCourseId(id));

                return ResultGenerator.genSuccessResult(d);
            }
        }
    }

    @Override
    public Result list(Course course) {

        if (null == course){
            course = new Course();
        }

        PageHelper.startPage(course.getPage() == null ? 0 : course.getPage(), course.getLimit() == null ? 10 : course.getLimit());
        course.setIsDelete(false);
        List<Course> list = courseMapper.list(course);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
