package com.github.kawis77.workshop.endproject.dao.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @NotEmpty
    @ToString.Exclude
    private String password;
    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<SongEntity> songs = new ArrayList<>();

    }

