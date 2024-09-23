package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.Teacher;
import com.fedoraa.presencebackend.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{idTeacher}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String idTeacher) {
        Teacher teacher = teacherService.getTeacherById(idTeacher);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{idTeacher}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String idTeacher, @RequestBody Teacher teacher) {
        teacher.setIdTeacher(idTeacher);
        teacherService.updateTeacher(teacher);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/{idTeacher}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String idTeacher) {
        teacherService.deleteTeacher(idTeacher);
        return ResponseEntity.noContent().build();
    }
}
