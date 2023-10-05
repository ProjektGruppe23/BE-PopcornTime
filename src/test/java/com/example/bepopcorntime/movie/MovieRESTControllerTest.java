package com.example.bepopcorntime.movie;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(controllers = MovieRESTController.class, excludeAutoConfiguration = {DataSourceAutoConfiguration.class})
public class MovieRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    void getCurrentMovies() throws Exception {
        // Arrange
        Movie movie1 = new Movie();
        movie1.setStartDate(Date.valueOf("2023-01-01"));

        Movie movie2 = new Movie();
        movie2.setStartDate(Date.valueOf("2023-12-01"));

        List<Movie> movies = Arrays.asList(movie1);
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Act & Assert
        mockMvc.perform(get("/movies/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].startDate", is("2023-01-01")));
    }

    @Test
    void getUpcomingMovies() throws Exception {
        // Arrange
        Movie movie1 = new Movie();
        movie1.setStartDate(Date.valueOf("2023-01-01"));

        Movie movie2 = new Movie();
        movie2.setStartDate(Date.valueOf("2023-12-01"));

        List<Movie> movies = Arrays.asList(movie2);
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Act & Assert
        mockMvc.perform(get("/movies/upcoming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].startDate", is("2023-12-01")));
    }
}