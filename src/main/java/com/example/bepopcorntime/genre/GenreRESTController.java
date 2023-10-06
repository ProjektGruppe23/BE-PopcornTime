package com.example.bepopcorntime.genre;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenreRESTController {
    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/genres/{movieId}")
    public List<String> getGenresByMovieId(@PathVariable int movieId, HttpSession session) {
        List<Genre> genresByMovieId = genreRepository.findByMovieGenres_Movie_Id(movieId);
        List<String> genres = new ArrayList<>();

        for (Genre genre : genresByMovieId) {
            genres.add(genre.getType());
        }


        session.setAttribute("genres", genres);
        return genres;
    }
}
