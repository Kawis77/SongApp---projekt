package com.github.kawis77.workshop.endproject.service;

import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import com.github.kawis77.workshop.endproject.dao.repository.SongRepository;
import com.github.kawis77.workshop.endproject.service.mapper.SongMapper;
import com.github.kawis77.workshop.endproject.web.model.SongModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    public List<SongEntity> allSongs() {
        return songRepository.findAll();
    }

    public Optional<SongEntity> findById(long id) {
        return songRepository.findById(id);
    }

    public SongEntity create(SongModel songModel) {
        SongEntity songEntity = songMapper.fromModel(songModel);
        return songRepository.save(songEntity);
    }

    public void delete(SongEntity songEntity) {
        songRepository.delete(songEntity);
    }

}
