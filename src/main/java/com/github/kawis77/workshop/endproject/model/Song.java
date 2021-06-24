package com.github.kawis77.workshop.endproject.model;


import lombok.*;

import javax.persistence.*;

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


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Chords chords = new Chords();

}
