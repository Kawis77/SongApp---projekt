package com.github.kawis77.workshop.endproject.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String songName;
    @Column(nullable = false)
    private String songAuthor;
    @Column(nullable = false)
    private String songText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany()
    private List<Chords> chords = new ArrayList<>();

    public Song(String songName) {
        this.songName = songName;
    }
}
