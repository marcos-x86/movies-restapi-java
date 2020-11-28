package org.moviesrest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Utility class for run Spring Boot application.
 */
@SpringBootApplication
public class MoviesRestApiApplication {

    /**
     * Main method that performs the execution of the application.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MoviesRestApiApplication.class, args);
    }
}
