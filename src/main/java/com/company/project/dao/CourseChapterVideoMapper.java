package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.CourseChapterVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseChapterVideoMapper extends Mapper<CourseChapterVideo> {

    List<CourseChapterVideo> list(CourseChapterVideo courseChapterVideo);

    List<CourseChapterVideo> selectByCourseChapterId(Long courseChapterId);

    CourseChapterVideo detail(Long id);

    CourseChapterVideo getByChapterIdAndNumber(@Param("courseChapterId") Long courseChapterId, @Param("chapterNumber") Integer chapterNumber);

    Integer getCountByChapterVideoId(Long courseChapterId);
}