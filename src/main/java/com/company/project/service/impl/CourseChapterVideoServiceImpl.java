package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CourseChapterVideoMapper;
import com.company.project.model.CourseChapter;
import com.company.project.model.CourseChapterVideo;
import com.company.project.service.CourseChapterService;
import com.company.project.service.CourseChapterVideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseChapterVideoServiceImpl extends AbstractService<CourseChapterVideo> implements CourseChapterVideoService {

    @Resource
    private CourseChapterVideoMapper courseChapterVideoMapper;

    @Resource
    private CourseChapterService courseChapterService;

    @Override
    public Result list(CourseChapterVideo courseChapterVideo) {

        if (null == courseChapterVideo){
            courseChapterVideo = new CourseChapterVideo();
        }

        PageHelper.startPage(courseChapterVideo.getPage() == null ? 0 : courseChapterVideo.getPage(), courseChapterVideo.getLimit() == null ? 10 : courseChapterVideo.getLimit());
        courseChapterVideo.setIsDelete(false);
        List<CourseChapterVideo> list = courseChapterVideoMapper.list(courseChapterVideo);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public List<CourseChapterVideo> selectByCourseChapterId(Long courseChapterId) {
        return courseChapterVideoMapper.selectByCourseChapterId(courseChapterId);
    }

    @Override
    public Result detail(Long id) {

        if (null == id){
            return ResultGenerator.genSuccessResult(new CourseChapterVideo());
        }else {
            CourseChapterVideo courseChapterVideo = courseChapterVideoMapper.detail(id);
            if (null == courseChapterVideo){
                return ResultGenerator.genSuccessResult(new CourseChapterVideo());
            }else {

                Integer chapterNumber = new Integer(0);
                if (null == courseChapterVideo.getChapterNumber()){
                    chapterNumber = 1;
                }else {
                    chapterNumber = courseChapterVideo.getChapterNumber();
                }

                //上一章
                courseChapterVideo.setFrontCourseChapterVideo(courseChapterVideoMapper.getByChapterIdAndNumber(courseChapterVideo.getCourseChapterId(), chapterNumber - 1 ));

                //下一章
                courseChapterVideo.setAfterCourseChapterVideo(courseChapterVideoMapper.getByChapterIdAndNumber(courseChapterVideo.getCourseChapterId(), chapterNumber + 1 ));

                return ResultGenerator.genSuccessResult(courseChapterVideo);
            }
        }
    }

    @Override
    public Result add(CourseChapterVideo courseChapterVideo) {

        CourseChapter courseChapter = courseChapterService.findByIdAndValidDelete(courseChapterVideo.getCourseChapterId());
        if (null == courseChapter){
            return ResultGenerator.genFailResult("课程章节不存在，请重新选择");
        }

        Integer chapterNumber = courseChapterVideoMapper.getCountByChapterVideoId(courseChapterVideo.getCourseChapterId());
        if (null == chapterNumber){
            courseChapterVideo.setChapterNumber(1);
        }else {
            courseChapterVideo.setChapterNumber(chapterNumber + 1);
        }

        save(courseChapterVideo);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(courseChapterVideo);
        return result;
    }
}
