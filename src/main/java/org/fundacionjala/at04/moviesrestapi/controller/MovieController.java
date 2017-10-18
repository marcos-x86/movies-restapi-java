package org.fundacionjala.at04.moviesrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.fundacionjala.at04.moviesrestapi.model.Movie;
import org.fundacionjala.at04.moviesrestapi.repository.MovieCrudRepository;

/**
 * Movie controller class.
 */
@RestController
public class MovieController {

    /**
     * Private auto wired movie repository instance.
     */
    @Autowired
    private MovieCrudRepository movieRepository;

    /**
     * Get Request Handler.
     *
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    /**
     * Post Request Handler.
     *
     * @param movie request body.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.POST, value = "/movies")
    public Movie post(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    /**
     * Get request by Id Handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/movies/{id}")
    public Movie getById(@PathVariable String id) {
        return movieRepository.findOne(id);
    }

    /**
     * Put request handler.
     *
     * @param id    value
     * @param movie request body
     * @return response body
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/movies/{id}")
    public Movie put(@PathVariable String id, @RequestBody Movie movie) {
        Movie newMovie = movieRepository.findOne(id);
        if (!(movie.validFields() && newMovie != null)) {
            throw new IllegalArgumentException("Bad parameters");
        }
        newMovie.setTitle(movie.getTitle());
        newMovie.setYear(movie.getYear());
        newMovie.setImDBScore(movie.getImDBScore());
        newMovie.setSynopsis(movie.getSynopsis());
        movieRepository.save(newMovie);
        return newMovie;
    }

    /**
     * Delete request handler.
     *
     * @param id value.
     * @return response body
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/movies/{id}")
    public Movie delete(@PathVariable String id) {
        Movie movie = movieRepository.findOne(id);
        movieRepository.delete(movie);
        return movie;
    }
}
