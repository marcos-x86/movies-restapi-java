package org.fundacionjala.at04.moviesrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.fundacionjala.at04.moviesrestapi.model.Movie;
import org.fundacionjala.at04.moviesrestapi.repository.MovieCrudRepository;
import org.fundacionjala.at04.moviesrestapi.repository.MovieRepository;

/**
 * Movie controller class.
 */
@RestController
public class MovieController {

    private static final String INVALID_DATA = "Invalid Data";
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieCrudRepository movieCrudRepository;

    /**
     * Get Request Handler.
     *
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public Iterable<Movie> getAll() {
        return movieCrudRepository.findAll();
    }

    /**
     * Post Request Handler.
     *
     * @param movie request body.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.POST, value = "/movies")
    public ResponseEntity<?> post(@RequestBody Movie movie) {
        if (movie.invalidFields() || movieRepository.findByTitle(movie.getTitle()).size() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_DATA);
        }
        movieCrudRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    /**
     * Get request by Id Handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/movies/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Movie movie = movieCrudRepository.findOne(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_DATA);
    }

    /**
     * Put request handler.
     *
     * @param id    value
     * @param movie request body
     * @return response body
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/movies/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Movie movie) {
        Movie newMovie = movieCrudRepository.findOne(id);
        if (movie.invalidFields() || newMovie == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_DATA);
        }
        newMovie.setTitle(movie.getTitle());
        newMovie.setYear(movie.getYear());
        newMovie.setImDBScore(movie.getImDBScore());
        newMovie.setSynopsis(movie.getSynopsis());
        movieCrudRepository.save(newMovie);
        return ResponseEntity.ok(newMovie);
    }

    /**
     * Delete request handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/movies/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Movie movie = movieCrudRepository.findOne(id);
        if (movie != null) {
            movieCrudRepository.delete(movie);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_DATA);
    }
}
