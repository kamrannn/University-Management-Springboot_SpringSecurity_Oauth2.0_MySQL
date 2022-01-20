package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Teacher;
import org.fidel_fer.UniversityManagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfTeachers() {
        return teacherService.getListOfTeachers();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTeacher(@RequestParam(name = "id") Long teacherId) {
        return teacherService.deleteTeacherById(teacherId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getTeacherById(@RequestParam(name = "id") Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }
}
