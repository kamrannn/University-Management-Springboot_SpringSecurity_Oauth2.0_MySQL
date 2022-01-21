package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Classroom;
import org.fidel_fer.UniversityManagement.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public ResponseEntity<Object> createClassroom(Classroom classroom) {
        try {
            classroomRepository.save(classroom);
            return new ResponseEntity<>("Classroom: " + classroom.getId() + " has been added in the system", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListOfClassrooms() {
        try {
            List<Classroom> classroomList = classroomRepository.findAll();
            if (classroomList.isEmpty()) {
                return new ResponseEntity<>("There is no classroom in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(classroomList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateClassroom(Classroom classroom) {
        try {
            Optional<Classroom> optionalClassroom = classroomRepository.findById(classroom.getId());
            if (optionalClassroom.isPresent()) {
                classroomRepository.save(classroom);
                return new ResponseEntity<>("Classroom: " + classroom.getId() + " has been updated in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no classroom against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteClassroomById(String classroomId) {
        try {
            Optional<Classroom> classroom = classroomRepository.findById(classroomId);
            if (classroom.isPresent()) {
                classroomRepository.delete(classroom.get());
                return new ResponseEntity<>("Classroom has been deleted from the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no classroom against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getClassroomById(String classroomId) {
        try {
            Optional<Classroom> classroom = classroomRepository.findById(classroomId);
            if (classroom.isPresent()) {
                return new ResponseEntity<>(classroom, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no classroom against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
