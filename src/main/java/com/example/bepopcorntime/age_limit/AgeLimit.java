package com.example.bepopcorntime.age_limit;

import com.example.bepopcorntime.movie.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AgeLimit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;

    @OneToMany(mappedBy = "ageLimit")
    @JsonBackReference
    private List<Movie> movies = new ArrayList<>();
}

