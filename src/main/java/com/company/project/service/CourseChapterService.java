package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.CourseChapter;

import java.util.List;

public interface CourseChapterService extends Service<CourseChapter> {

    Result list(CourseChapter courseChapter);

    List<CourseChapter> selectByCourseId(Long courseId);

    Result detail(Long id);

    Result add(CourseChapter courseChapter);
}
