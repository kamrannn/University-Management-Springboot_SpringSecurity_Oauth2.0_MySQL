package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
