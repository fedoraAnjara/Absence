package com.fedoraa.presencebackend.controller;

import com.fedoraa.presencebackend.entity.CorProcess;
import com.fedoraa.presencebackend.service.CorProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/COR")
public class CorProcessController {

    private final CorProcessService corProcessService;

    public CorProcessController(CorProcessService corProcessService) {
        this.corProcessService = corProcessService;
    }

    @GetMapping
    public List<CorProcess> getAllCorProcesses() {
        return corProcessService.getAllCorProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorProcess> getCorProcessById(@PathVariable String id) {
        CorProcess corProcess = corProcessService.getCorProcessById(id);
        if (corProcess != null) {
            return ResponseEntity.ok(corProcess);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createCorProcess(@RequestBody CorProcess corProcess) {
        corProcessService.saveCorProcess(corProcess);
    }

    @DeleteMapping("/{id}")
    public void deleteCorProcess(@PathVariable String id) {
        corProcessService.deleteCorProcess(id);
    }
}
