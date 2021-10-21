package com.github.kawis77.workshop.endproject.web.controller;


import com.github.kawis77.workshop.endproject.dao.SongDao;
import com.github.kawis77.workshop.endproject.dao.entity.ChordsEntity;
import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import com.github.kawis77.workshop.endproject.service.ChordsService;
import com.github.kawis77.workshop.endproject.service.SongService;
import com.github.kawis77.workshop.endproject.web.model.SongRequest;
import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.dao.repository.ChordsRepository;
import com.github.kawis77.workshop.endproject.dao.repository.SongRepository;
import com.github.kawis77.workshop.endproject.dao.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.logging.Logger;

@RequestMapping("/song")
@Controller
public class SongController {
    private static final Logger LOGGER = Logger.getLogger(SongController.class.getName());

    private final SongService songService;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ChordsService chordsService;
    private final SongDao songDao;

    public SongController(SongService songService, UserRepository userRepository, SongRepository songRepository, ChordsRepository chordsRepository, ChordsService chordsService, SongDao songDao) {
        this.songService = songService;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.chordsService = chordsService;
        this.songDao = songDao;
    }


    @GetMapping("/findbychords")
    public String prepareFindBySong(Model model) {
        model.addAttribute("chords", chordsService.allChords());
        return "song/findall";
    }

    @PostMapping("/findbychords")
    public String processEdit(Model model, SongRequest songRequest) {
        LOGGER.info("processEdit(" + songRequest + ")");
        List<ChordsEntity> chords = chordsService.findAllById(songRequest.getChords());
        Set<SongEntity> songs = new HashSet<>();
        for (ChordsEntity chord : chords) {
            songs.addAll(chord.getSongs());
        }

        model.addAttribute("songs", songs);
        return "song/findallbysongready";
    }

    @GetMapping("/onesong/{id}")
    public String prepareOneSong(Model model, @PathVariable Long id) {
        model.addAttribute("onesongs", songRepository.findAll());
//        model.addAttribute("onesong", songDao.findById(id));
        Optional<SongEntity> optionalSong = songRepository.findById(id);
        //TODO Optional sam z siebie rzuca NoSuchElementException, więc to jest niepotrzebne
        SongEntity song = optionalSong.orElseThrow(() -> new NoSuchElementException());
        if (optionalSong.isPresent()) {
            model.addAttribute("onesong", song);
            return "song/onesong";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/user-menu")
    public String prepareUserMenu(Model model) {

        model.addAttribute("usermenu", songRepository.findAll());

        return "song/user-menu";
    }


    @GetMapping("/list")
    public String prepareList(Model model) {
        //TODO Chyba tutaj powinny być tylko dla zalogowanego usera :)
        //     finaAllBy
        model.addAttribute("songs", songRepository.findAll());

        return "song/list";
    }


    @GetMapping("/newsong")
    public String addSong(Model model, Principal userUser) {
        model.addAttribute("addsong", new SongEntity());
        model.addAttribute("chords", chordsService.allChords());
        return "song/create-songs";
    }

    @PostMapping("/newsong")
    public String addSong(SongEntity song, @RequestParam(name = "username") String username) {
        LOGGER.info("addSong(" + song + "," + username + ")");
//         Chords checkChords = chordsRepository.findById(id);
//         chordsList.add(checkChords);
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        UserEntity user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        song.setUser(user);
        songRepository.save(song);
        SongEntity savedSong = songRepository.save(song);
        LOGGER.info("savedSong: " + savedSong);
        return "redirect:/song/list";

    }

    @GetMapping("/edit/{id}")
    public String prepareEdit(@PathVariable Long id, Model model) {
        model.addAttribute("editsong", songDao.findById(id));
        model.addAttribute("chords",  chordsService.allChords());
        return "song/edit-songs";
    }

    @PostMapping("/edit/{id}")
    public String processEdit(SongEntity song, BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "song/user-menu";
        }
        songRepository.save(song);
        return "redirect:/song/list";
    }

    @GetMapping("/delete/{id}")
    public String prepareDelete(@PathVariable Long id, Model model) {
        model.addAttribute("deletesong", songDao.findById(id));
        return "/song/delete-song";
    }

    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        SongEntity song = songDao.findById(id);
        songRepository.delete(song);
        return "/song/list";
    }

}