package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.Teacher;
import com.fedoraa.presencebackend.repository.TeacherDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherDAO teacherRepository;

    public TeacherService(TeacherDAO teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.update(teacher);
    }

    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }
}
