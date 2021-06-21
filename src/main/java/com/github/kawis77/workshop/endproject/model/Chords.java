package com.github.kawis77.workshop.endproject.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "chords")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Chords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String chordOne;
    @Column
    private String chordTwo;
    @Column
    private String chordTree;
    @Column
    private String chordFour;
    @Column
    private String chordFive;
    @Column
    private String chordSix;
}
