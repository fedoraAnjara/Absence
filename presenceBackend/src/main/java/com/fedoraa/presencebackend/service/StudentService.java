package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.Student;
import com.fedoraa.presencebackend.repository.StudentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentDAO studentRepository;

    public StudentService(StudentDAO studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
