package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.CourseChapterVideo;

import java.util.List;

public interface CourseChapterVideoService extends Service<CourseChapterVideo> {

    Result list(CourseChapterVideo courseChapterVideo);

    List<CourseChapterVideo> selectByCourseChapterId(Long courseChapterId);

    Result detail(Long id);

    Result add(CourseChapterVideo courseChapterVideo);
}
