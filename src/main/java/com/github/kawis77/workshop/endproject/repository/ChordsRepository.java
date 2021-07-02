package com.github.kawis77.workshop.endproject.repository;

import com.github.kawis77.workshop.endproject.model.Chords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChordsRepository extends JpaRepository<Chords , Long> {
    Optional<Chords> findById(Long id);
}
