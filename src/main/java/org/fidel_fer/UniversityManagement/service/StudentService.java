package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Student;
import org.fidel_fer.UniversityManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Object> createStudent(Student student) {
        try {
            studentRepository.save(student);
            return new ResponseEntity<>("Student: " + student.getFirstName() + " has been added in the system", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListOfStudents() {
        try {
            List<Student> studentList = studentRepository.findAll();
            if (studentList.isEmpty()) {
                return new ResponseEntity<>("There is no student in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(studentList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateStudent(Student student) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(student.getId());
            if (optionalStudent.isPresent()) {
                studentRepository.save(student);
                return new ResponseEntity<>("Student: " + student.getFirstName() + " has been updated in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no student against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteStudentById(String studentId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                studentRepository.delete(student.get());
                return new ResponseEntity<>("Student has been deleted from the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no student against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStudentById(String studentId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no student against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
