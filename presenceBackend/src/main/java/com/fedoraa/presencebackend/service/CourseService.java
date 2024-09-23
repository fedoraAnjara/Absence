package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.Course;
import com.fedoraa.presencebackend.repository.CourseDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseDAO courseDAO;

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    public Course getCourseById(String idCourse) {
        return courseDAO.findById(idCourse);
    }

    public void createCourse(Course course) {
        courseDAO.save(course);
    }

    public void updateCourse(String idCourse, Course course) {
        // On assure que l'ID correspond à celui dans l'objet course pour éviter les incohérences
        Course existingCourse = courseDAO.findById(idCourse);
        if (existingCourse != null) {
            course.setIdCourse(idCourse);
            courseDAO.update(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    public void deleteCourse(String idCourse) {
        courseDAO.deleteById(idCourse);
    }
}
