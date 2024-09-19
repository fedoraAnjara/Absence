package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
    Student findById(String id);
    void save(Student student);
    void update(Student student);
    void deleteById(String id);
}
