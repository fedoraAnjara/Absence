package com.fedoraa.presencebackend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {
    private String firstName;
    private String lastName;
    private String email;
    private Genre genre;
    private String birthDate;
}
