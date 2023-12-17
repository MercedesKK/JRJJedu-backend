package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Course;

import java.util.List;

public interface CourseMapper extends Mapper<Course> {

    List<Course> selectByCourseTypeId(Long courseTypeId);

    Course detail(Long id);

    List<Course> list(Course course);
}