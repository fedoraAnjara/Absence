package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.Student;
import com.fedoraa.presencebackend.service.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
@AllArgsConstructor
public class AbsenceController {
    private final AbsenceService absenceService;

    @GetMapping
    public List<Absence> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @PostMapping
    public void addAbsence(@RequestBody Absence absence) {
        absenceService.addAbsence(absence);
    }

    @DeleteMapping("/{id_student}")
    public void deleteStudent(@PathVariable String id_student) {
        absenceService.deleteStudent(id_student);
    }

}
