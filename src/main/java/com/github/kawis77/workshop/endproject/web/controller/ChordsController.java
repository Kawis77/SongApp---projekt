package com.github.kawis77.workshop.endproject.web.controller;


import com.github.kawis77.workshop.endproject.dao.repository.ChordsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chords")
public class ChordsController {

    private final ChordsRepository chordsRepository;

    public ChordsController(ChordsRepository chordsRepository) {
        this.chordsRepository = chordsRepository;
    }

}