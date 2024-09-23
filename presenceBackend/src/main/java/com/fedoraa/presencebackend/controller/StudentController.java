package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.Student;
import com.fedoraa.presencebackend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id_student}")
    public Student getStudentById(@PathVariable String id_student) {
        return studentService.getStudentById(id_student);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @PutMapping("/{id_student}")
    public void updateStudent(@PathVariable String id_student, @RequestBody Student student) {
        student.setIdStudent(id_student);
        studentService.updateStudent(student);
    }

    @DeleteMapping("/{id_student}")
    public void deleteStudent(@PathVariable String id_student) {
        studentService.deleteStudent(id_student);
    }
}
