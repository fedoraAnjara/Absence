package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    List<Teacher> findAll();
    Teacher findById(String idTeacher);
    void save(Teacher teacher);
    void update(Teacher teacher);
    void deleteById(String idTeacher);
}
