package com.fedoraa.presencebackend.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {
    private String idStudent;
    private String observation;
    private String groupId;

}
