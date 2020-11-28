package org.moviesrest.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.moviesrest.api.model.Movie;

/**
 * Movie CRUD Repository class.
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    /**
     * Find one Movie by Id.
     *
     * @param id movie id.
     * @return movie object.
     */
    Movie findOne(String id);

    /**
     * Find movies by title.
     *
     * @param title movie title.
     * @return list of movie objects.
     */
    List<Movie> findByTitle(String title);

    /**
     * Delete movie.
     *
     * @param movie object.
     */
    void delete(Movie movie);
}
