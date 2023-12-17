package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.CourseChapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseChapterMapper extends Mapper<CourseChapter> {

    List<CourseChapter> list(CourseChapter courseChapter);

    List<CourseChapter> selectByCourseId(Long courseId);

    CourseChapter getByChapterIdAndNumber(@Param("courseId") Long courseId, @Param("chapterNumber") Integer chapterNumber);

    Integer getCountByCourseId(Long courseId);
}