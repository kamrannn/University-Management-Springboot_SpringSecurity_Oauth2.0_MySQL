package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}
