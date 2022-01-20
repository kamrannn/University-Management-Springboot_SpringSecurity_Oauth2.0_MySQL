package org.fidel_fer.UniversityManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @Column(unique = true)
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private int age;
    private String password;
    private String phoneNo;
    @ManyToMany(targetEntity = Subject.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Subject> subjectList = new ArrayList<>();
}
