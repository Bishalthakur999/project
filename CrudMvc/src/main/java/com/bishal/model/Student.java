package com.bishal.model;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SerdeImport
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String gender;

   // private  Integer totalCost;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name ="course_student",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;

    public Student(Integer id, String name, String address, String gender, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.courses = new ArrayList<>();
    }

    public Integer getTotalCoursePrice() {
        return courses.stream().mapToInt(Course::getCFees).sum();
    }







}
