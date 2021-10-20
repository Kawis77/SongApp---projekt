package com.github.kawis77.workshop.endproject.dao.repository;
import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    void deleteSongById(SongEntity id);



}