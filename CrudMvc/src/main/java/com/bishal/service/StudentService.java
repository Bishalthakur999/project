package com.bishal.service;

import com.bishal.model.Student;
import com.bishal.reposotory.StudentRepo;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student );
    public List<Student> findAllStudent();
    public Student findStudentById(Integer id);
    public Student updateStudent(Student student,Integer id,Integer courseId);
    public void deleteStudent(Integer id);

    public Student findMaxCostStudent();

    public Student enrollCourses(Integer id,List<Integer> courseId);

    public Integer getTotalCoursePriceByStudent(Integer studentId);



//    public Student findStudentWithMaxTotalCoursePrice();
}
