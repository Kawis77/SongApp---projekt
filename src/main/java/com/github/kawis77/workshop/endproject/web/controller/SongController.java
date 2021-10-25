package com.github.kawis77.workshop.endproject.web.controller;


import com.github.kawis77.workshop.endproject.dao.SongDao;
import com.github.kawis77.workshop.endproject.dao.entity.ChordsEntity;
import com.github.kawis77.workshop.endproject.dao.entity.SongEntity;
import com.github.kawis77.workshop.endproject.service.ChordsService;
import com.github.kawis77.workshop.endproject.service.SongService;
import com.github.kawis77.workshop.endproject.web.model.SongModel;
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
        model.addAttribute("onesongs", songService.allSongs());
        Optional<SongEntity> optionalSong = songService.findById(id);
//        SongEntity song = optionalSong.orElseThrow(() -> new NoSuchElementException());
//        if (optionalSong.isPresent()) {
            model.addAttribute("onesong",optionalSong);
            return "song/onesong";
//        } else {
//            return "redirect:/list";
//        }
    }

    @GetMapping("/user-menu")
    public String prepareUserMenu(Model model) {

        model.addAttribute("usermenu", songService.allSongs());

        return "song/user-menu";
    }


    @GetMapping("/list")
    public String prepareList(Model model) {
        model.addAttribute("songs", songRepository.findAll());

        return "song/list";
    }


    @GetMapping("/newsong")
    public String addSong(Model model) {
        model.addAttribute("addsong", new SongEntity());
        model.addAttribute("chords", chordsService.allChords());
        return "song/create-songs";
    }

    @PostMapping("/newsong")
    public String addSong(SongModel songModel, @RequestParam(name = "username") String username) {
        LOGGER.info("addSong(" + songModel + "," + username + ")");
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        UserEntity user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        songModel.setUser(user);
        SongEntity savedSong = songService.create(songModel);
        LOGGER.info("savedSong: " + savedSong);
        return "redirect:/song/list";

    }

    @GetMapping("/edit/{id}")
    public String prepareEdit(@PathVariable Long id, Model model) {
        Optional<SongEntity> optionalSong= songService.findById(id);
        model.addAttribute("editsong", optionalSong.get());
        model.addAttribute("chords",  chordsService.allChords());
        return "song/edit-songs";
    }

    @PostMapping("/edit/{id}")
    public String processEdit(SongModel songModel, BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "song/user-menu";
        }
        songService.create(songModel);
        return "redirect:/song/list";
    }

    @GetMapping("/delete/{id}")
    public String prepareDelete(@PathVariable Long id, Model model) {
        Optional<SongEntity> optionalSongEntity = songService.findById(id);
        model.addAttribute("deletesong", optionalSongEntity.get());
        return "/song/delete-song";
    }

    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        Optional<SongEntity> optionalSongEntity = songService.findById(id);
        SongEntity song = optionalSongEntity.get();
        songRepository.delete(song);
        return "/song/list";
    }

}