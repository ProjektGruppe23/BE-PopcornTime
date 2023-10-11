package com.example.bepopcorntime.age_limit;

import com.example.bepopcorntime.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class AgeLimit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;

    @OneToMany(mappedBy = "ageLimit")
    private Set<Movie> movies = new HashSet<>();

}

