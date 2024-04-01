package com.bishal.controller;

import com.bishal.model.Student;
import com.bishal.reposotory.StudentRepo;
import com.bishal.service.StudentService;
import io.micronaut.http.annotation.*;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    private final StudentRepo studentRepo;

    public StudentController(StudentService studentService, StudentRepo studentRepo) {
        this.studentService = studentService;
        this.studentRepo = studentRepo;
    }

    @Get("/findall")
    public List<Student> getall(){
       return studentService.findAllStudent();
    }

    @Get("/find/{id}")
    public Student getStudent(Integer id){
        return studentService.findStudentById(id);
    }

    @Delete("/delete/{id}")
    public void delete(Integer id){
        studentService.deleteStudent(id);
    }

    @Post("/create")
    public Student create(@Body Student student){
       return studentService.createStudent(student);
    }

    @Post("/update/{id}/{courseId}")
    public Student update(@Body Student student,Integer id,Integer courseId){
        return studentService.updateStudent(student,id,courseId);
    }

    @Get("/findmax")
    public Student findMaxCostStudent(){
        return studentService.findMaxCostStudent();

    }

    @Post("/{studentId}/endroll")
    public  Student endrollCourse(Integer studentId,@Body List<Integer> courseId){
        return studentService.enrollCourses(studentId,courseId);
    }

    @Get("/maximum")
    public Student maximum(){
        return studentRepo.findStudentWithMaxTotalCoursePrice();
    }

//    @Get("/maxPrice")
//    public Student findStudentWithMaxTotalCoursePrice() {
//        return studentService.findStudentWithMaxTotalCoursePrice();
//    }

}
