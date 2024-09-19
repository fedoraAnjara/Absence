package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.Student;
import com.fedoraa.presencebackend.service.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
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

    @PostMapping("/check")
    public void checkAbsencesAndConvocate(@RequestBody Student student) {
        absenceService.checkAbsencesAndConvocate(student);
    }
}
