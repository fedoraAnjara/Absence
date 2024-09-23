package com.fedoraa.presencebackend.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CorProcess {
    private String idProcess;
    private String reason;
    private Student student;

    public CorProcess(String idProcess, String reason, Student student) {
        this.idProcess = idProcess;
        this.reason = reason;
        this.student = student;
    }

    public CorProcess() {

    }

    public void setIdStudent(String idStudent) {
        this.student.setIdStudent(idStudent);
    }
}
