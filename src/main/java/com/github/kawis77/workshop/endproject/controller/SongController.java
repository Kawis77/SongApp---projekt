package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.dao.SongDao;
import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.model.SongRequest;
import com.github.kawis77.workshop.endproject.model.User;
import com.github.kawis77.workshop.endproject.repository.ChordsRepository;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import com.github.kawis77.workshop.endproject.repository.UserRepository;
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

    //TODO W kolejnej wersji aplikacji wykorzystać serwisy zamiast repozytoriów :)
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ChordsRepository chordsRepository;
    //TODO Repozytoria mają pełną funkcjonalność entityManager, więc tak długo jak nie robimy
    //     hack magic, to nie używamy entityManager/dao
    private final SongDao songDao;

    public SongController(UserRepository userRepository, SongRepository songRepository, ChordsRepository chordsRepository, SongDao songDao) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.chordsRepository = chordsRepository;
        this.songDao = songDao;
    }


    @GetMapping("/findbychords")
    public String prepareFindbySong(Model model) {
        model.addAttribute("chords", chordsRepository.findAll());
        return "song/findall";
    }

    @PostMapping("/findbychords")
    public String processEdit(Model model, SongRequest songRequest) {
        LOGGER.info("processEdit(" + songRequest + ")");
        List<Chords> chords = chordsRepository.findAllById(songRequest.getChords());

        Set<Song> songs = new HashSet<>();
        for (Chords chord : chords) {
            songs.addAll(chord.getSongs());
        }

        model.addAttribute("songs", songs);
        return "song/findallbysongready";
    }

    @GetMapping("/onesong/{id}")
    public String prepareOneSong(Model model, @PathVariable Long id) {
        model.addAttribute("onesongs", songRepository.findAll());
//        model.addAttribute("onesong", songDao.findById(id));
        Optional<Song> optionalSong = songRepository.findById(id);
        //TODO Optional sam z siebie rzuca NoSuchElementException, więc to jest niepotrzebne
        Song song = optionalSong.orElseThrow(() -> new NoSuchElementException());
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
        model.addAttribute("addsong", new Song());
        model.addAttribute("chords", chordsRepository.findAll());
        return "song/create-songs";
    }

    @PostMapping("/newsong")
    public String addSong(Song song, @RequestParam(name = "username") String username) {
        LOGGER.info("addSong(" + song + "," + username + ")");
//         Chords checkChords = chordsRepository.findById(id);
//         chordsList.add(checkChords);
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        song.setUser(user);
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
    public String prepareDelete(@PathVariable Long id, Model model) {
        model.addAttribute("deletesong", songDao.findById(id));
        return "/song/delete-song";
    }

    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        Song song = songDao.findById(id);
        songRepository.delete(song);
        return "/song/list";
    }

}