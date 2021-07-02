package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.ChordsRepository;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RequestMapping("/song")
@Controller
public class SongController {
    private static final Logger LOGGER = Logger.getLogger(SongController.class.getName());

    private final SongRepository songRepository;
    private final ChordsRepository chordsRepository;

    public SongController(SongRepository songRepository, ChordsRepository chordsRepository) {
        this.songRepository = songRepository;
        this.chordsRepository = chordsRepository;
    }

    @GetMapping("/user-menu")
    public String prepareUserMenu(Model model) {
        model.addAttribute("usermenu", songRepository.findAll());

        return "song/user-menu";
    }


    @GetMapping("/list")
    public String prepareList(Model model) {
        model.addAttribute("songs", songRepository.findAll());

        return "song/list";
    }


    @GetMapping("/newsong")
    public String addSong(Model model) {
        model.addAttribute("addsong", new Song());
//        model.addAttribute("chord" , new Chords());
        model.addAttribute("chords", Arrays.asList(
                new Chords(1L, "A"),
                new Chords(2L, "C"),
                new Chords(3L, "F"),
                new Chords(4L, "G")
        ));
        return "song/create-songs";
    }

    @PostMapping("/newsong")
    public String addSong(Song song) {
        LOGGER.info("addSong(" + song + ")");
//         Chords checkChords = chordsRepository.findById(id);
//         chordsList.add(checkChords);
         songRepository.save(song);
        Song savedSong = songRepository.save(song);
        LOGGER.info("savedSong: " + savedSong);
        return "song/list";

    }

    @GetMapping("/edit")
    public String prepareEdit(Long id, Model model) {
        model.addAttribute("author", songRepository.findById(id));
        return "song/edit-song";
    }

    @PostMapping("/edit")
    public String processEdit(Song song, BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "authors/edit-form";
        }
        songRepository.save(song);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String prepareDelete(Long id, Model model) {
        model.addAttribute("deletesong", songRepository.findById(id));
        return "song/delete-songs";
    }

    @PostMapping("/delete")
    public String processDelete(Long id) {
        Song song = songRepository.findAllById(id);
        songRepository.delete(song);
        return "redirect:/song/list";
    }

}
