package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.Course;
import com.fedoraa.presencebackend.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> getCourseById(@PathVariable String idCourse) {
        Course course = courseService.getCourseById(idCourse);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return new ResponseEntity<>("Course created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{idCourse}")
    public ResponseEntity<String> updateCourse(@PathVariable String idCourse, @RequestBody Course course) {
        try {
            courseService.updateCourse(idCourse, course);
            return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCourse}")
    public ResponseEntity<String> deleteCourse(@PathVariable String idCourse) {
        courseService.deleteCourse(idCourse);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }
}
