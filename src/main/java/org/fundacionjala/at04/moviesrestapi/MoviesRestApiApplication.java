package org.fundacionjala.at04.moviesrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Utility class for run Spring Boot application.
 */
@SpringBootApplication
public final class MoviesRestApiApplication {

    /**
     * Private constructor for utility class.
     */
    private MoviesRestApiApplication() {

    }

    /**
     * Main method that performs the execution of the application.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MoviesRestApiApplication.class, args);
    }
}
