package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Classroom;
import org.fidel_fer.UniversityManagement.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfClassrooms(@RequestHeader(name = "Authorization") String token) {
        return classroomService.getListOfClassrooms();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addClassroom(@RequestHeader(name = "Authorization") String token, @RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateClassroom(@RequestHeader(name = "Authorization") String token, @RequestBody Classroom classroom) {
        return classroomService.updateClassroom(classroom);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteClassroom(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") String classroomId) {
        return classroomService.deleteClassroomById(classroomId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getClassroomById(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") String classroomId) {
        return classroomService.getClassroomById(classroomId);
    }

}
