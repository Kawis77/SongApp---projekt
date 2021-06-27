package com.github.kawis77.workshop.endproject.repository;

import com.github.kawis77.workshop.endproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
