package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.Student;
import com.fedoraa.presencebackend.repository.AbsenceDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AbsenceService {
    private final AbsenceDAO absenceDAO;

    public List<Absence> getAllAbsences() {
        return absenceDAO.findAll();
    }

    // Ajouter cette méthode
    public void addAbsence(Absence absence) {
        absenceDAO.save(absence);
    }

    /*public void checkAbsencesAndConvocation(Student student) {
        int unjustifiedAbsences = absenceDAO.countUnjustifiedAbsences(student.getIdStudent());

        if (unjustifiedAbsences >= 3) {
            ConvocationCOR convocation = new ConvocationCOR();
            convocation.setStudentId(Long.valueOf(student.getIdStudent()));
            convocation.setDateConvocation(new Date());
            convocation.setDescription("Convocation au COR pour absences justifiable.");

            convocationCORDAO.save(convocation);
        }
    }*/
}
