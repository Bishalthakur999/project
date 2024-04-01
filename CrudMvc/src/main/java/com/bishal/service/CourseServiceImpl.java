package com.bishal.service;

import com.bishal.exception.UserNotFoundException;
import com.bishal.model.Course;
import com.bishal.model.Student;
import com.bishal.reposotory.CourseRepo;
import com.bishal.reposotory.StudentRepo;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    private final StudentRepo studentRepo;
    @Override
    public Course createCourse(@Body Course course) {
        //Optional<Student> student=studentRepo.findById(studentId);
        return courseRepo.save(course);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public Course findCouresById(Integer id) {
       Optional<Course> cours=courseRepo.findById(id);
        if (cours.isPresent()) {
            return cours.get();
        }
        throw new UserNotFoundException("course not found");

    }
}
