package com.github.kawis77.workshop.endproject.repository;

import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


    Optional<Song> findById(Long id);

    void deleteSongById(Song id);

   List <Song> findSongByChords(Chords chords);


}