package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.CorProcess;

import java.util.List;

public interface CorProcessDAO {
    void save(CorProcess corProcess);
    CorProcess findById(String idProcess);
    List<CorProcess> findAll();
    void deleteById(String idProcess);
}
