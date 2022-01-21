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
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(targetEntity = Teacher.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Teacher> teachersList = new ArrayList<>();

    @ManyToMany(targetEntity = Group.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> groupList = new ArrayList<>();

    @ManyToMany(targetEntity = Classroom.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Classroom> classroomList = new ArrayList<>();

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> studentList = new ArrayList<>();
}
