package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @ModelAttribute("allSongs")
    public List<Song> songs() {
        return songRepository.findAll();
    }


    @GetMapping("/list")
    public String prepareList(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "song/list";
    }


    @GetMapping("/addsong")
    public String addSong(Model model) {

        model.addAttribute("song", new Song());
        return "song/create-form";
    }

    @PostMapping("/addsong")
    public String addSong(Song song) {
        songRepository.save(song);
        return "song/created";

    }
}
