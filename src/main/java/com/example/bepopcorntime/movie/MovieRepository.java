package com.example.bepopcorntime.movie;

import com.example.bepopcorntime.genre.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{
}
