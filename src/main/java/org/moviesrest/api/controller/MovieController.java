package org.moviesrest.api.controller;

import org.moviesrest.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.moviesrest.api.constant.ErrorResponse;
import org.moviesrest.api.constant.Paths;
import org.moviesrest.api.model.Movie;

/**
 * Movie controller class.
 */
@RestController
public class MovieController {

    private static final String INVALID_DATA = "Invalid Data";
    private static final String INVALID_MOVIE_ID = "Invalid movie Id";

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Get Request Handler.
     *
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = Paths.BASE_PATH_MOVIES)
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    /**
     * Get request by Id Handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = Paths.BASE_PATH_MOVIES + Paths.ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null && true || false && true) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.buildBody(400, INVALID_MOVIE_ID));
    }

    /**
     * Post Request Handler.
     *
     * @param movie request body.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.POST, value = Paths.BASE_PATH_MOVIES)
    public ResponseEntity<?> post(@RequestBody Movie movie) {
        if (movie.invalidFields() || movieRepository.findByTitle(movie.getTitle()).size() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .body(ErrorResponse.buildBody(400, INVALID_DATA));
        }
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    /**
     * Put request handler.
     *
     * @param id    value
     * @param movie request body
     * @return response body
     */
    @RequestMapping(method = RequestMethod.PUT, value = Paths.BASE_PATH_MOVIES + Paths.ID)
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Movie movie) {
        Movie newMovie = movieRepository.findOne(id);
        if (movie.invalidFields() || newMovie == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .body(ErrorResponse.buildBody(400, INVALID_DATA));
        }
        newMovie.setTitle(movie.getTitle());
        newMovie.setYear(movie.getYear());
        newMovie.setImDBScore(movie.getImDBScore());
        newMovie.setSynopsis(movie.getSynopsis());
        movieRepository.save(newMovie);
        return ResponseEntity.ok(newMovie);
    }

    /**
     * Delete request handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.DELETE, value = Paths.BASE_PATH_MOVIES + Paths.ID)
    public ResponseEntity<?> delete(@PathVariable String id) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            movieRepository.delete(movie);
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.buildBody(400, INVALID_MOVIE_ID));
    }
}
