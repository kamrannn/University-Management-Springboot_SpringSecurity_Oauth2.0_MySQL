package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Student;
import org.fidel_fer.UniversityManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfStudents() {
        return studentService.getListOfStudents();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteStudent(@RequestParam(name = "id") String studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getStudentById(@RequestParam(name = "id") String studentId) {
        return studentService.getStudentById(studentId);
    }
}
