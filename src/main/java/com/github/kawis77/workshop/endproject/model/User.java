package com.github.kawis77.workshop.endproject.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NotEmpty
    private String userName;
    @Column
    @Email
    private String email;
    @Column(nullable = false)
    @NotEmpty
    private String password;
    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Song> songs = new ArrayList<>();
}
