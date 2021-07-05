package com.github.kawis77.workshop.endproject.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chords")
@Getter
@Setter
@ToString(exclude = "songs")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Chords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false)
    private String name;


//    @ManyToOne()
//    @JoinColumn(name = "songs_id")
//    private Song songs;

    @ManyToMany(mappedBy = "chords")
    private List<Song> songs = new ArrayList<>();

//    public Chords(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
