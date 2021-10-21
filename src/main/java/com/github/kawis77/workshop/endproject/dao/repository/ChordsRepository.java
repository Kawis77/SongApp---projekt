package com.github.kawis77.workshop.endproject.dao.repository;

import com.github.kawis77.workshop.endproject.dao.entity.ChordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChordsRepository extends JpaRepository<ChordsEntity, Long> {
    Optional<ChordsEntity> findById(Long id);
    @Override
    List<ChordsEntity> findAll();
}
