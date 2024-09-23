package com.fedoraa.presencebackend.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    private String idAbsence;
    private LocalDateTime date;
    private Course course;
    private Student student;
    private boolean isJustified; // Nouveau champ
}
