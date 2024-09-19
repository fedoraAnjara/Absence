package com.fedoraa.presencebackend.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String idCourse;
    private String title;
    private Teacher teacher;
}

