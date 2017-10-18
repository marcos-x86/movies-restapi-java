package org.fundacionjala.at04.moviesrestapi.repository;

import org.springframework.data.repository.CrudRepository;

import org.fundacionjala.at04.moviesrestapi.model.Movie;

/**
 * Created by Marcos.
 */
public interface MovieCrudRepository extends CrudRepository<Movie, String> {
    @Override
    Movie findOne(String id);

    @Override
    void delete(Movie movie);
}
