package com.bishal.reposotory;

import com.bishal.model.Student;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentRepoTest {

    private final StudentRepo studentRepo;

    StudentRepoTest(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Inject
    EntityManager entityManager;

    @Test
    void findStudentWithMaxTotalCoursePrice() {

        Student student=new Student();
        when(entityManager.createQuery(
                "SELECT s FROM Student s JOIN s.courses c GROUP BY s.id ORDER BY SUM(c.cFees) DESC", Student.class))
                .thenReturn((TypedQuery<Student>) Mockito.mock(Query.class));
        when(studentRepo.findStudentWithMaxTotalCoursePrice()).thenReturn(student);

        Student result=studentRepo.findStudentWithMaxTotalCoursePrice();
    }
}