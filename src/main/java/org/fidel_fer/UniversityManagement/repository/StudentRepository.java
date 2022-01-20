package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
