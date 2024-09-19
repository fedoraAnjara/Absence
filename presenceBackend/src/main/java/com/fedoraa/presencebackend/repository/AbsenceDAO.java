package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Absence;

import java.util.List;

public interface AbsenceDAO {
    List<Absence> findAll();
    Absence findById(String id);
    void save(Absence absence);
    void update(Absence absence);
    void deleteById(String id);
    int countUnjustifiedAbsences(String studentId);
}
