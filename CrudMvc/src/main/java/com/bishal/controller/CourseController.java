package com.bishal.controller;

import com.bishal.model.Course;
import com.bishal.reposotory.CourseRepo;
import com.bishal.service.CourseService;
import io.micronaut.http.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Post("/create/course")
    public Course createCourse(@Body Course course){
        return courseService.createCourse(course);
    }

    @Get("/get/all")
   public List<Course> findall(){
        return courseService.findAllCourse();
   }

   @Get("/findbyid/{id}")
   public Course findById(@PathVariable Integer id){
        return courseService.findCouresById(id);
   }

}
