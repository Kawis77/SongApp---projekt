package com.github.kawis77.workshop.endproject.dao.repository;

import com.github.kawis77.workshop.endproject.dao.entity.ChordsEntity;
import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {


//    Optional<Song> findById(Long id);

    void deleteSongById(SongEntity id);

   List <SongEntity> findSongByChords(ChordsEntity chords);


}