package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Group;
import org.fidel_fer.UniversityManagement.model.Student;
import org.fidel_fer.UniversityManagement.model.Subject;
import org.fidel_fer.UniversityManagement.model.Teacher;
import org.fidel_fer.UniversityManagement.repository.StudentRepository;
import org.fidel_fer.UniversityManagement.repository.SubjectRepository;
import org.fidel_fer.UniversityManagement.repository.TeacherRepository;
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
    private final TeacherRepository teacherRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
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
                subject.get().getStudentList().add(student1);
                /*subjectRepository.save(subject.get());*/
            }
            subjectRepository.save(subject.get());
            return new ResponseEntity<>(subject.get().getName() + " is assigned to the students", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no subject against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> assignTeacherToSubject(Long teacherId, Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                subject.get().getTeachersList().add(teacher.get());
                subjectRepository.save(subject.get());
                return new ResponseEntity<>("Teacher " + teacher.get().getFirstName() + " is assigned to the the subject " + subject.get().getName(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no teacher against this id", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("There is no subject against this id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> createGroupsForSubjects(List<Group> groupList, Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            for (Group group : groupList
            ) {
                subject.get().getGroupList().add(group);
            }
            subjectRepository.save(subject.get());
            return new ResponseEntity<>("Groups has been made for the subject " + subject.get().getName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no subject against this id", HttpStatus.OK);
        }
    }


}
