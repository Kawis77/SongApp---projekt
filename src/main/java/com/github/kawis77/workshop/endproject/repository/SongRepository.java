package com.github.kawis77.workshop.endproject.repository;

import com.github.kawis77.workshop.endproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

Song findBySongName(String name);
Song removeBySongName(Song name);

}
