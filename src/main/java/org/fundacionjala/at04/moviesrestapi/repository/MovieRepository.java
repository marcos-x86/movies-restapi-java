package org.fundacionjala.at04.moviesrestapi.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import org.fundacionjala.at04.moviesrestapi.model.Movie;

/**
 * Movie Repository class.
 */
public interface MovieRepository extends Repository<Movie, String> {

    /**
     * Perform a query to find Movies with a specified title.
     *
     * @param title movie title.
     * @return a list with the query results.
     */
    List<Movie> findByTitle(String title);
}
