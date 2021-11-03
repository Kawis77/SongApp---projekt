package com.github.kawis77.workshop.endproject.dao.repository;
import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    void deleteSongById(SongEntity id);

    @Query(
            value = "SELECT * FROM S c WHERE username = ':username' ORDER BY username ASC LIMIT 1",
            nativeQuery = true
    )
    public SongEntity findAllByUsername(@Param("username") String username );



}