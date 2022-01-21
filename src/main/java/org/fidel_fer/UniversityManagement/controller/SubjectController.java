package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Classroom;
import org.fidel_fer.UniversityManagement.model.Group;
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
    public ResponseEntity<Object> listOfSubjects(@RequestHeader(name = "Authorization") String token) {
        return subjectService.getListOfSubjects();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addSubject(@RequestHeader(name = "Authorization") String token, @RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateSubject(@RequestHeader(name = "Authorization") String token, @RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteSubject(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") Long subjectId) {
        return subjectService.deleteSubjectById(subjectId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getSubjectById(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") Long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping("/AssignSubjectToStudents")
    public ResponseEntity<Object> assignSubjectToStudents(@RequestHeader(name = "Authorization") String token, @RequestBody List<Student> studentList, @RequestParam(name = "subjectId") Long subjectId) {
        return subjectService.assignSubjectToStudents(studentList, subjectId);
    }

    @PostMapping("/AssignTeacherToSubject")
    public ResponseEntity<Object> assignTeacherToSubject(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "teacherId") Long teacherId, @RequestParam(name = "subjectId") Long subjectId) {
        return subjectService.assignTeacherToSubject(teacherId, subjectId);
    }

    @PostMapping("/createGroupsForSubject")
    public ResponseEntity<Object> createGroupsForSubject(@RequestHeader(name = "Authorization") String token, @RequestBody List<Group> groupList, @RequestParam(name = "subjectId") Long subjectId) {
        return subjectService.createGroupsForSubjects(groupList, subjectId);
    }

    @PostMapping("/assignClassroomToSubject")
    public ResponseEntity<Object> assignClassroomToSubject(@RequestHeader(name = "Authorization") String token, @RequestBody List<Classroom> classroomList, @RequestParam(name = "subjectId") Long subjectId) {
        return subjectService.assignClassroomToASubject(classroomList, subjectId);
    }

}
