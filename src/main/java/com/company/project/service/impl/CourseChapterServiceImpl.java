package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CourseChapterMapper;
import com.company.project.model.Course;
import com.company.project.model.CourseChapter;
import com.company.project.service.CourseChapterService;
import com.company.project.service.CourseChapterVideoService;
import com.company.project.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseChapterServiceImpl extends AbstractService<CourseChapter> implements CourseChapterService {

    @Resource
    private CourseChapterMapper courseChapterMapper;

    @Resource
    private CourseChapterVideoService courseChapterVideoService;

    @Resource
    private CourseService courseService;

    @Override
    public Result list(CourseChapter courseChapter) {

        if (null == courseChapter){
            courseChapter = new CourseChapter();
        }

        PageHelper.startPage(courseChapter.getPage() == null ? 0 : courseChapter.getPage(), courseChapter.getLimit() == null ? 10 : courseChapter.getLimit());
        courseChapter.setIsDelete(false);
        List<CourseChapter> list = courseChapterMapper.list(courseChapter);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public List<CourseChapter> selectByCourseId(Long courseId) {
        List<CourseChapter> courseChapterList = courseChapterMapper.selectByCourseId(courseId);
        if (courseChapterList != null && courseChapterList.size() > 0){
            for (CourseChapter d:courseChapterList) {
                d.setCourseChapterVideoList(courseChapterVideoService.selectByCourseChapterId(d.getId()));
            }
        }
        return courseChapterList;
    }

    @Override
    public Result detail(Long id) {

        if (null == id){
            return ResultGenerator.genSuccessResult(new CourseChapter());
        }else {
            CourseChapter courseChapter = findById(id);
            if (null == courseChapter){
                return ResultGenerator.genSuccessResult(new CourseChapter());
            }else {

                Integer chapterNumber = new Integer(0);
                if (null == courseChapter.getChapterNumber()){
                    chapterNumber = 1;
                }else {
                    chapterNumber = courseChapter.getChapterNumber();
                }
                //上一章
                courseChapter.setFrontCourseChapter(courseChapterMapper.getByChapterIdAndNumber(courseChapter.getCourseId(), chapterNumber - 1));

                //下一章
                courseChapter.setAfterCourseChapter(courseChapterMapper.getByChapterIdAndNumber(courseChapter.getCourseId(), chapterNumber + 1));
                return ResultGenerator.genSuccessResult(courseChapter);
            }
        }
    }

    @Override
    public Result add(CourseChapter courseChapter) {

        Course course = courseService.findByIdAndValidDelete(courseChapter.getCourseId());
        if (null == course){
            return ResultGenerator.genFailResult("课程不存在，请重新选择");
        }

        Integer chapterNumber = courseChapterMapper.getCountByCourseId(courseChapter.getCourseId());
        if (null == chapterNumber){
            courseChapter.setChapterNumber(1);
        }else {
            courseChapter.setChapterNumber(chapterNumber + 1);
        }

        save(courseChapter);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(courseChapter);
        return result;
    }
}
