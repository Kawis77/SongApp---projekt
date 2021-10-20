package com.github.kawis77.workshop.endproject.dao.entity;


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
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


//    @Column(name = "chords")
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Chords> chords = new ArrayList<>();

    @ManyToMany
    @ToString.Exclude
    private List<ChordsEntity> chords = new ArrayList<>();
}
