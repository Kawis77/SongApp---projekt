package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song/")
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @GetMapping("/list")
    public List<Song> listOfSong() {
        return songRepository.findAll();
    }


    @GetMapping("/add") // Metoda jest wywoływana w reakcji na żądanie GET /create-person
    public String prepare() {
        return "song/create-form"; // Zwracamy identyfikator widoku, który zostanie odnaleziony na podstawie PREFIX + identyfikator + SUFIX ---> ma to dać poprawną ścieżkę do pliku patrząc z perspektywy katalogu webapp
    }

    @PostMapping("/add")
    public String process(@RequestParam() String songName, @RequestParam String songAuthor, @RequestParam String songText, Model model) {
        Song song= new Song();
        song.setSongName(songName);
        song.setSongAuthor(songAuthor);
        song.setSongText(songText);
        songRepository.save(song);
        model.addAttribute("song", song);
        return "song/created";
    }


}
