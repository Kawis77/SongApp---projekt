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

    @GetMapping("/onesong/{id}")
    public String prepareOneSong(Model model , @PathVariable Long id)  {
        model.addAttribute("onesongs",songRepository.findAll());
        model.addAttribute("onesong", songRepository.findById(id));
        return "song/onesong";
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

    @GetMapping("/edit/{id}")
    public String prepareEdit( @PathVariable Long id, Model model) {
        model.addAttribute("editsong", songRepository.findAll());
        return "song/edit-songs";
    }

    @PostMapping("/edit")
    public String processEdit(Song song, BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "/user-menu";
        }
        songRepository.save(song);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String prepareDelete( @PathVariable Long id, Model model , Song song1) {
        model.addAttribute("deletesong", songRepository.findById(id));
        return "song/user-menu";
    }

    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        Song song = songRepository.findAllById(id);
        songRepository.delete(song);
        return "redirect:/song/list";
    }

}
