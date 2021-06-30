package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/song")
@Controller
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @GetMapping("/list")
    public String prepareList(Model model) {
        model.addAttribute("songs", songRepository.findAll());

        return "song/songs";
    }


    @GetMapping("/newsong")
    public String addSong(Model model) {
        model.addAttribute("addsong" , new Song());
        return "song/create-songs";
    }

    @PostMapping("/addsong")
    public String addSong(Song song) {
        songRepository.save(song);
        return "song/songs";

    }
    @GetMapping("/delete")
    public String prepareDelete(Long id ,Model model) {
        model.addAttribute("deletesong", songRepository.findById(id));
        return "song/delete-songs";
    }

    @PostMapping("/delete")
    public String processDelete(Long id) {
        Song song = songRepository.findAllById(id);
        songRepository.delete(song);
        return "redirect:/song/songs";
    }

}
