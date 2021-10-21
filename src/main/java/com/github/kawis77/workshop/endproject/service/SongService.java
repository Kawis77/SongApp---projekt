package com.github.kawis77.workshop.endproject.service;

import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import com.github.kawis77.workshop.endproject.dao.repository.SongRepository;
import com.github.kawis77.workshop.endproject.service.mapper.SongMapper;
import com.github.kawis77.workshop.endproject.web.model.SongModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    public List<SongEntity> allSongs(){
        return songRepository.findAll();
    }

}
