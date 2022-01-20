package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
