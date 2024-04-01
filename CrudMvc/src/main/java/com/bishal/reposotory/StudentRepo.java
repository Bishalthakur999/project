package com.bishal.reposotory;

import com.bishal.model.Student;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {



  // @Query("SELECT s FROM Student s JOIN student_course cs ON s.id = cs.student_id JOIN Course c ON cs.course_id = c.id WHERE c.cFees = (SELECT MAX(c1.cFees) FROM Course c1)")
   @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.cFees = (SELECT MAX(c1.cFees) FROM Course c1 )")
   public Student findMaxCost();

    @Query("SELECT s FROM Student s JOIN s.courses c GROUP BY s.id ORDER BY SUM(c.cFees) DESC LIMIT 1")
   public Student findStudentWithMaxTotalCoursePrice();
}
