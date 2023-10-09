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

// This annotation specifies that this is a Spring Boot test focused on just the web layer.
// It also disables database auto-configuration to avoid connecting to the actual database.
@WebMvcTest(controllers = MovieRESTController.class, excludeAutoConfiguration = {DataSourceAutoConfiguration.class})
public class MovieRESTControllerTest {

    // MockMvc is auto-wired to allow simulating HTTP requests against the controllers.
    @Autowired
    private MockMvc mockMvc;

    // Mocking the actual repository using Mockito, integrated within the Spring context.
    @MockBean
    private MovieRepository movieRepository;

    // Test case for the "getCurrentMovies" method in the MovieRESTController
    @Test
    void getCurrentMovies() throws Exception {
        // Arrange section: Setting up test data and mock behavior
        Movie movie1 = new Movie();
        movie1.setStartDate(Date.valueOf("2023-01-01"));

        Movie movie2 = new Movie();
        movie2.setStartDate(Date.valueOf("2023-12-01"));

        // Here, we simulate what the repository should return when findAll() is called.
        List<Movie> movies = Arrays.asList(movie1);
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Act & Assert: Performing the mock HTTP request and verifying the response
        mockMvc.perform(get("/movies/current"))  // Simulating a GET request to the endpoint
                .andExpect(status().isOk())  // Expecting a 200 OK status
                .andExpect(jsonPath("$", hasSize(1)))  // Expecting a JSON array of size 1
                .andExpect(jsonPath("$[0].startDate", is("2023-01-01")));  // Expecting the startDate of the first object to be "2023-01-01"
    }

    // Test case for the "getUpcomingMovies" method in the MovieRESTController
    @Test
    void getUpcomingMovies() throws Exception {
        // Arrange section: Setting up test data and mock behavior
        Movie movie1 = new Movie();
        movie1.setStartDate(Date.valueOf("2023-01-01"));

        Movie movie2 = new Movie();
        movie2.setStartDate(Date.valueOf("2023-12-01"));

        // Mocking what the repository should return when findAll() is called.
        List<Movie> movies = Arrays.asList(movie2);
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Act & Assert: Performing the mock HTTP request and verifying the response
        mockMvc.perform(get("/movies/upcoming"))  // Simulating a GET request to the endpoint
                .andExpect(status().isOk())  // Expecting a 200 OK status
                .andExpect(jsonPath("$", hasSize(1)))  // Expecting a JSON array of size 1
                .andExpect(jsonPath("$[0].startDate", is("2023-12-01")));  // Expecting the startDate of the first object to be "2023-12-01"
    }
}