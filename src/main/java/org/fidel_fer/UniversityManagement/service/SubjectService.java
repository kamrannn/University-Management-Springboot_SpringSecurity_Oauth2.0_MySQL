package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Student;
import org.fidel_fer.UniversityManagement.model.Subject;
import org.fidel_fer.UniversityManagement.repository.StudentRepository;
import org.fidel_fer.UniversityManagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Object> createSubject(Subject subject) {
        try {
            subjectRepository.save(subject);
            return new ResponseEntity<>("Subject: " + subject.getName() + " has been added in the system", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListOfSubjects() {
        try {
            List<Subject> subjectList = subjectRepository.findAll();
            if (subjectList.isEmpty()) {
                return new ResponseEntity<>("There is no subject in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(subjectList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateSubject(Subject subject) {
        try {
            Optional<Subject> optionalSubject = subjectRepository.findById(subject.getId());
            if (optionalSubject.isPresent()) {
                subjectRepository.save(subject);
                return new ResponseEntity<>("Subject: " + subject.getName() + " has been updated in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no subject against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteSubjectById(Long subjectId) {
        try {
            Optional<Subject> subject = subjectRepository.findById(subjectId);
            if (subject.isPresent()) {
                subjectRepository.delete(subject.get());
                return new ResponseEntity<>("Subject has been deleted from the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no subject against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSubjectById(Long subjectId) {
        try {
            Optional<Subject> subject = subjectRepository.findById(subjectId);
            if (subject.isPresent()) {
                return new ResponseEntity<>(subject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no subject against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> assignSubjectToStudents(List<Student> studentList, Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            for (Student student : studentList
            ) {
                Student student1 = studentRepository.getById(student.getId());
                student1.getSubjectList().add(subject.get());
                studentRepository.save(student1);
            }
            return new ResponseEntity<>(subject.get().getName() + " is assigned to the students", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no subject against this Id", HttpStatus.OK);
        }
    }

}
