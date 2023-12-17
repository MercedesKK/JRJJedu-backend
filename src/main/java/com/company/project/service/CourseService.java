package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Course;

import java.util.List;

public interface CourseService extends Service<Course> {

    List<Course> selectByCourseTypeId(Long courseTypeId);

    Result detail(Long id);

    Result list(Course course);
}
