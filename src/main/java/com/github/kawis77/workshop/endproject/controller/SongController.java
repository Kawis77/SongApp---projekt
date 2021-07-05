package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.dao.SongDao;
import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.ChordsRepository;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.logging.Logger;

@RequestMapping("/song")
@Controller
public class SongController {
    private static final Logger LOGGER = Logger.getLogger(SongController.class.getName());

    private final SongRepository songRepository;
    private final ChordsRepository chordsRepository;
    private final SongDao songDao;

    public SongController(SongRepository songRepository, ChordsRepository chordsRepository, SongDao songDao) {
        this.songRepository = songRepository;
        this.chordsRepository = chordsRepository;
        this.songDao = songDao;
    }

    @GetMapping("/onesong/{id}")
    public String prepareOneSong(Model model, @PathVariable Long id) {
        model.addAttribute("onesongs", songRepository.findAll());
        model.addAttribute("onesong", songDao.findById(id));
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
        model.addAttribute("chords", chordsRepository.findAll());
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
        return "redirect:/song/list";

    }

    @GetMapping("/edit/{id}")
    public String prepareEdit(@PathVariable Long id, Model model) {
        model.addAttribute("editsong", songDao.findById(id));
        model.addAttribute("chords", chordsRepository.findAll());
        return "song/edit-songs";
    }

    @PostMapping("/edit/{id}")
    public String processEdit(Song song, BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "song/user-menu";
        }
        songRepository.save(song);
        return "redirect:/song/list";
    }

    @GetMapping("/delete/{id}")
    public String prepareDelete(@PathVariable Long id, Model model, Song song1) {
        model.addAttribute("deletesong", songDao.findById(id));
        return "/song/delete-song";
    }

    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        Song song = songDao.findById(id);
        songRepository.deleteSongById(song);
        return "/song/list";
    }

}