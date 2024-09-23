package com.fedoraa.presencebackend.service;

import com.fedoraa.presencebackend.entity.CorProcess;
import com.fedoraa.presencebackend.repository.CorProcessDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorProcessService {

    private final CorProcessDAO corProcessDAO;

    public CorProcessService(CorProcessDAO corProcessDAO) {
        this.corProcessDAO = corProcessDAO;
    }

    public List<CorProcess> getAllCorProcesses() {
        return corProcessDAO.findAll();
    }

    public CorProcess getCorProcessById(String idProcess) {
        return corProcessDAO.findById(idProcess);
    }

    public void saveCorProcess(CorProcess corProcess) {
        corProcessDAO.save(corProcess);
    }

    public void deleteCorProcess(String idProcess) {
        corProcessDAO.deleteById(idProcess);
    }
}
