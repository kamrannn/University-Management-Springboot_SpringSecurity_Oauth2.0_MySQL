package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Student;
import org.fidel_fer.UniversityManagement.model.Subject;
import org.fidel_fer.UniversityManagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfSubjects() {
        return subjectService.getListOfSubjects();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateSubject(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteSubject(@RequestParam(name = "id") Long subjectId) {
        return subjectService.deleteSubjectById(subjectId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getSubjectById(@RequestParam(name = "id") Long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping("/AssignSubjectToStudents")
    public ResponseEntity<Object> assignSubjectToStudents(@RequestBody List<Student> studentList, @RequestParam(name = "subjectId") Long subjectId) {
        return subjectService.assignSubjectToStudents(studentList, subjectId);
    }
}
