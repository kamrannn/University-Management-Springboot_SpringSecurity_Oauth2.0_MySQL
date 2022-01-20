package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Teacher;
import org.fidel_fer.UniversityManagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<Object> createTeacher(Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return new ResponseEntity<>("Teacher: " + teacher.getFirstName() + " has been added in the system", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListOfTeachers() {
        try {
            List<Teacher> teacherList = teacherRepository.findAll();
            if (teacherList.isEmpty()) {
                return new ResponseEntity<>("There is no teacher in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(teacherList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateTeacher(Teacher teacher) {
        try {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getId());
            if (optionalTeacher.isPresent()) {
                teacherRepository.save(teacher);
                return new ResponseEntity<>("Teacher: " + teacher.getFirstName() + " has been updated in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no teacher against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteTeacherById(Long teacherId) {
        try {
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                teacherRepository.delete(teacher.get());
                return new ResponseEntity<>("Teacher has been deleted from the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no teacher against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTeacherById(Long teacherId) {
        try {
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                return new ResponseEntity<>(teacher, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no teacher against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
