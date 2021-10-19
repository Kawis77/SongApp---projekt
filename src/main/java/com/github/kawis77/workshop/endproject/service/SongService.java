package com.github.kawis77.workshop.endproject.service;

import com.github.kawis77.workshop.endproject.dao.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void saveSong(){

    }
}
