package com.bishal.service;

import com.bishal.exception.UserNotFoundException;
import com.bishal.model.Course;
import com.bishal.model.Student;
import com.bishal.reposotory.CourseRepo;
import com.bishal.reposotory.StudentRepo;
import io.micronaut.http.annotation.Body;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    private final CourseRepo courseRepo;

    @Override
    public Student createStudent(@Body Student student) {


//       var optionalCourse= courseRepo.findById(courseId);
//        optionalCourse.ifPresent(course -> student.setCourses(Arrays.asList(course)));

        return studentRepo.save(student);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        Optional<Student> stu = studentRepo.findById(id);
        if (stu.isPresent()) {
            return stu.get();
        }

        throw new UserNotFoundException("");

    }

    @Override
    public Student updateStudent(Student student, Integer id,Integer courseId) {
        Optional<Student> stu = studentRepo.findById(id);

        if (stu.isEmpty()) {
            throw new UserNotFoundException("User not found from given id:"+id);
        }

        Student oldStudent = stu.get();
        if (student.getName() != null) {
            oldStudent.setName(student.getName());
        }
        if (student.getAddress() != null) {
            oldStudent.setAddress(student.getAddress());
        }
        if (student.getGender() != null) {
            oldStudent.setGender(student.getGender());
        }


            var optionalCourse= courseRepo.findById(courseId);
            optionalCourse.ifPresent(course -> oldStudent.setCourses(Arrays.asList(optionalCourse.get())));



//        if (student.getCourses() != null) {
//            oldStudent.setCourses(student.getCourses());
//        }

        return studentRepo.update(oldStudent);

    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepo.findById(id);
        studentRepo.deleteById(id);
    }

    @Override
    public Student findMaxCostStudent() {

        return studentRepo.findMaxCost();
    }

    @Transactional
    @Override
    public Student enrollCourses(Integer id, List<Integer> courseId) {
        Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("user not"));
//         if(student.isEmpty()){
//             throw new UserNotFoundException("user not found");
//         }
         List<Course> courses=new ArrayList<>();

         for(Integer courseIds : courseId){
             //Optional<Course> course=courseRepo.findById(courseIds);
//             if(courseId.isEmpty()){
//                 throw new UserNotFoundException("user not found");
//             }
              Course course=courseRepo.findById(courseIds).orElseThrow(()->new RuntimeException("course not found"));
             courses.add(course);

         }

         student.getCourses().addAll(courses);


        return studentRepo.save(student);
    }

    @Override
    public Integer getTotalCoursePriceByStudent(Integer studentId) {
        Student student = findStudentById(studentId);
        return student.getTotalCoursePrice();
    }

//    @Override
//    public Student findStudentWithMaxTotalCoursePrice() {
//        List<Student> students=studentRepo.findAll();
//        Student studentWithMaxPrice = null;
//        Integer maxTotalPrice = 0;
//        for (Student student : students) {
//            Integer totalCoursePrice = student.getTotalCoursePrice();
//            if (totalCoursePrice > maxTotalPrice) {
//                maxTotalPrice = totalCoursePrice;
//                studentWithMaxPrice = student;
//            }
//        }
//        return studentWithMaxPrice;
//
//    }
}
