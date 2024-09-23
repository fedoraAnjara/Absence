package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Course;

import java.util.List;

public interface CourseDAO {
    List<Course> findAll();
    Course findById(String idCourse);
    void save(Course course);
    void update(Course course);
    void deleteById(String idCourse);
}
