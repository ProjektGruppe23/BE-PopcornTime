package com.example.bepopcorntime.age_limit;

import com.example.bepopcorntime.movie.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AgeLimit
{
    @OneToOne(mappedBy = "ageLimit")
    @JsonBackReference
    private Movie movie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
}

