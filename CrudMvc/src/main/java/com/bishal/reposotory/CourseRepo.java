package com.bishal.reposotory;

import com.bishal.model.Course;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
}
