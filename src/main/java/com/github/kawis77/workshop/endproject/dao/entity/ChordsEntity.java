package com.github.kawis77.workshop.endproject.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chords")
@Getter
@Setter
@ToString//(exclude = "songs")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ChordsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false)
    private String name;

    @ManyToMany(mappedBy = "chords")
    private List<SongEntity> songs = new ArrayList<>();

}
