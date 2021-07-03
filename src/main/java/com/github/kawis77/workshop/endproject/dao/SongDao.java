package com.github.kawis77.workshop.endproject.dao;


import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class SongDao {

    @PersistenceContext
    protected EntityManager em;

    public void save(Song entity) {
        em.persist(entity);
    }

    public Song update(Song entity) {
        return em.merge(entity);
    }

    public Song findById(Long id) {
        return em.find(Song.class, id);
    }

    public void remove(Song entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }



//    public List<Song> findByChords() {
//        return em.createQuery("select Song from Song where chords = 'A' , C, Chords.class).getResultList();
//    }
}
