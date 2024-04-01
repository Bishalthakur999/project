package com.bishal.service;

import com.bishal.model.Course;
import com.bishal.model.Student;

import java.util.List;

public interface CourseService {
    public Course createCourse(Course course );
    public List<Course> findAllCourse();
    public Course findCouresById(Integer id);
}
