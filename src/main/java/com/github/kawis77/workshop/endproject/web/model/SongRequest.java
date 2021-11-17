package com.github.kawis77.workshop.endproject.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongRequest {

    private List<Long> chords = new ArrayList<>();
}
