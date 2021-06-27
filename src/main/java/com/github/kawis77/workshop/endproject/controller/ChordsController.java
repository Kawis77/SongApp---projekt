package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.Chords;
import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.ChordsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ChordsController {

    private final ChordsRepository chordsRepository;

    public ChordsController(ChordsRepository chordsRepository) {
        this.chordsRepository = chordsRepository;
    }

    @GetMapping("/addchords")
    public String addChord(Model model) {
        model.addAttribute("addchords", new Chords());
        return "chord/create-chords";
    }

    @PostMapping("/chords")
    public String addChords(@ModelAttribute Chords chords) {
        chordsRepository.save(chords);
        return "chord/create-chords";
    }
}