package com.github.kawis77.workshop.endproject.dao;


import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional
public class SongDao {

    @PersistenceContext
    protected EntityManager em;

    public void save(SongEntity entity) {
        em.persist(entity);
    }

    public SongEntity update(SongEntity entity) {
        return em.merge(entity);
    }

    public SongEntity findById(Long id) {
        return em.find(SongEntity.class, id);
    }

    public void remove(SongEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }



//    public List<Song> findByChords() {
//        return em.createQuery("select Song from Song where chords = 'A' , C, Chords.class).getResultList();
//    }
}
