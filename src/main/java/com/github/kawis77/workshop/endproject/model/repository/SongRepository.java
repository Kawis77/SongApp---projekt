package com.github.kawis77.workshop.endproject.model.repository;

import com.github.kawis77.workshop.endproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findSongBySongNameAndAndSongAuthor(String songName , String songAuthor);

    List<Song> findAllByChords(String chords);

    void deleteById(Long id);

}
