package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.CorProcess;
import com.fedoraa.presencebackend.repository.AbsenceDAO;
import com.fedoraa.presencebackend.repository.CorProcessDAO;
import com.fedoraa.presencebackend.repository.StudentDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AbsenceService {
    private final AbsenceDAO absenceDAO;
    private final CorProcessDAO corProcessDAO;
    private final StudentDAO studentDAO;

    public List<Absence> getAllAbsences() {
        return absenceDAO.findAll();
    }

    public void addAbsence(Absence absence) {
        absenceDAO.save(absence);
        if (absenceDAO.countUnjustifiedAbsences(absence.getStudent().getIdStudent()) >= 3) {
            CorProcess corProcess = new CorProcess();
            corProcess.setIdProcess(UUID.randomUUID().toString());
            corProcess.setReason("3 absences with no justification");
            corProcess.setIdStudent(absence.getStudent().getIdStudent());
            corProcessDAO.save(corProcess);
        }
    }

    public void deleteStudent(String idStudent) {
        absenceDAO.deleteById(idStudent);
    }
}
