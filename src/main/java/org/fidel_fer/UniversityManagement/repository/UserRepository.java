package org.fidel_fer.UniversityManagement.repository;

import org.fidel_fer.UniversityManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsername(String username);
}
