package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @GetMapping("/list")
    public List<Song> listOfSong() {
        return songRepository.findAll();
    }


    @GetMapping() // Metoda jest wywoływana w reakcji na żądanie GET /create-person
    public String addSong(Model model) {
        // Aby zbindować formularz Spring Form do obietku z modelu musimy w tym modelu
        // coś umieścić -> clever!
        model.addAttribute("song", new Song());
        return "song/create-form"; // Zwracamy identyfikator widoku, który zostanie odnaleziony na podstawie PREFIX + identyfikator + SUFIX ---> ma to dać poprawną ścieżkę do pliku patrząc z perspektywy katalogu webapp
    }

    @PostMapping
    public String addSong(Song song) {
        songRepository.save(song);
        return "song/created";

    }
}
