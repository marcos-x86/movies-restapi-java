package org.fundacionjala.at04.moviesrestapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.fundacionjala.at04.moviesrestapi.constant.Paths;
import org.fundacionjala.at04.moviesrestapi.model.Movie;
import org.fundacionjala.at04.moviesrestapi.repository.MovieRepository;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Movie Controller Tests class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@DirtiesContext
public class MovieControllerTest {

    private static final String EXPRESSION_ID = "$.id";
    private static final String EXPRESSION_TITLE = "$.title";
    private static final String EXPRESSION_YEAR = "$.year";
    private static final String EXPRESSION_IMDB_SCORE = "$.imDBScore";
    private static final String EXPRESSION_SYNOPSIS = "$.synopsis";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieRepository movieRepo;

    /**
     * Test movies endpoint without arguments.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void getAllMovies() throws Exception {
        mvc.perform(get(Paths.BASE_PATH_MOVIES).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test get movie by id with valid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void getMovieById() throws Exception {
        final String movieId = "0045660001";
        final String movieTitle = "The Man Who Knew Infinity";
        final Integer movieYear = 2015;
        final Double movieScore = 7.2;
        final String movieSynopsis = "he story of the life of the pioneer Indian mathematician, Srinivasa Ramanujan";

        Movie movie = new Movie(movieTitle, movieYear, movieScore, movieSynopsis);
        movie.setId(movieId);

        given(movieRepo.findOne(movieId)).willReturn(movie);
        mvc.perform(get(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(EXPRESSION_ID, is(movieId)))
                .andExpect(jsonPath(EXPRESSION_TITLE, is(movieTitle)))
                .andExpect(jsonPath(EXPRESSION_YEAR, is(movieYear)))
                .andExpect(jsonPath(EXPRESSION_IMDB_SCORE, is(movieScore)))
                .andExpect(jsonPath(EXPRESSION_SYNOPSIS, is(movieSynopsis)));
    }

    /**
     * Test post movie with valid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void postMovie() throws Exception {
        final String movieTitle = "Interstellar";
        final Integer movieYear = 2014;
        final Double movieScore = 8.6;
        final String movieSynopsis = "A team of explorers travel through a wormhole in space.";
        Movie movie = new Movie(movieTitle, movieYear, movieScore, movieSynopsis);

        given(movieRepo.save(movie)).willReturn(movie);
        mvc.perform(post(Paths.BASE_PATH_MOVIES).contentType(MediaType.APPLICATION_JSON).content(movie.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(EXPRESSION_TITLE, is(movieTitle)))
                .andExpect(jsonPath(EXPRESSION_YEAR, is(movieYear)))
                .andExpect(jsonPath(EXPRESSION_IMDB_SCORE, is(movieScore)))
                .andExpect(jsonPath(EXPRESSION_SYNOPSIS, is(movieSynopsis)));
    }

    /**
     * Test put movie with valid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void putMovie() throws Exception {
        final String movieId = "105549871";
        final String movieTitle = "Collateral Beauty";
        final Integer movieYear = 2016;
        final Double movieScore = 6.8;
        final String movieSynopsis = "Retreating from life after a tragedy, a man questions the universe.";
        Movie movie = new Movie(movieTitle, movieYear, movieScore, movieSynopsis);
        movie.setId(movieId);

        given(movieRepo.findOne(movieId)).willReturn(movie);
        final Integer newYear = 2017;
        movie.setYear(newYear);
        mvc.perform(put(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON)
                .content(movie.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(EXPRESSION_TITLE, is(movieTitle)))
                .andExpect(jsonPath(EXPRESSION_YEAR, is(newYear)))
                .andExpect(jsonPath(EXPRESSION_IMDB_SCORE, is(movieScore)))
                .andExpect(jsonPath(EXPRESSION_SYNOPSIS, is(movieSynopsis)));
    }

    /**
     * Test delete movie with valid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void deleteMovie() throws Exception {
        final String movieId = "105561";
        final String movieTitle = "Rogue One";
        final Integer movieYear = 2016;
        final Double movieScore = 7.9;
        final String movieSynopsis = "The Rebel Alliance makes a risky move to steal the plans for the Death Star.";
        Movie movie = new Movie(movieTitle, movieYear, movieScore, movieSynopsis);
        movie.setId(movieId);

        given(movieRepo.findOne(movieId)).willReturn(movie);
        mvc.perform(delete(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(EXPRESSION_ID, is(movieId)))
                .andExpect(jsonPath(EXPRESSION_TITLE, is(movieTitle)))
                .andExpect(jsonPath(EXPRESSION_YEAR, is(movieYear)))
                .andExpect(jsonPath(EXPRESSION_IMDB_SCORE, is(movieScore)))
                .andExpect(jsonPath(EXPRESSION_SYNOPSIS, is(movieSynopsis)));
    }

    /**
     * Test get movie by id with invalid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void getMovieByIdInvalid() throws Exception {
        final String movieId = "002200";

        given(movieRepo.findOne(movieId)).willReturn(null);
        mvc.perform(get(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test post movie with invalid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void postMovieInvalid() throws Exception {
        final String title = "The Perks of Being a Wallflower";
        final Integer year = 2012;
        final Double imDBScore = 8.0;
        String movieBody = String.format("{\"title\":\"%s\",\"year\":%d,\"imDBScore\":%.1f}",
                title, year, imDBScore);

        mvc.perform(post(Paths.BASE_PATH_MOVIES).contentType(MediaType.APPLICATION_JSON).content(movieBody))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test put movie with invalid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void putMovieInvalid() throws Exception {
        final String movieId = "101";
        final String movieTitle = "American Hustle";
        final Integer movieYear = 2013;
        final Double movieScore = 7.3;
        final String movieSynopsis = "A con man, Irving Rosenfeld is forced to work for a wild FBI agent";
        Movie movie = new Movie(movieTitle, movieYear, movieScore, movieSynopsis);
        movie.setId(movieId);

        given(movieRepo.findOne(movieId)).willReturn(movie);
        String moviePutBody = String.format("{\"title\":\"%s\",\"year\":%d,\"synopsis\":%s}",
                movieTitle, movieYear, movieSynopsis);
        mvc.perform(put(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON)
                .content(moviePutBody))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test put movie with invalid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void putMovieInvalidFind() throws Exception {
        final String movieId = "101";
        final String movieTitle = "Hacksaw Ridge";
        final Integer movieYear = 2016;
        final String movieSynopsis = "American Army Medic Desmond T. Doss, who served during the Battle of Okinawa.";

        given(movieRepo.findOne(movieId)).willReturn(null);
        String moviePutBody = String.format("{\"title\":\"%s\",\"year\":%d,\"synopsis\":%s}",
                movieTitle, movieYear, movieSynopsis);
        mvc.perform(put(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON)
                .content(moviePutBody))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test get movie by id with invalid parameters.
     *
     * @throws Exception generic exception.
     */
    @Test
    public void deleteMovieByIdInvalid() throws Exception {
        final String movieId = "66586";

        given(movieRepo.findOne(movieId)).willReturn(null);
        mvc.perform(delete(Paths.BASE_PATH_MOVIES_WITH.concat(movieId)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
