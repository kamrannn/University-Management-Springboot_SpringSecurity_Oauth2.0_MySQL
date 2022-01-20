package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
