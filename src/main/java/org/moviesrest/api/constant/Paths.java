package org.moviesrest.api.constant;

/**
 * Constants Paths utility class.
 */
public final class Paths {

    private static final String VERSION = "/api/v1";
    private static final String MOVIES = "/movies";
    public static final String BASE_PATH_MOVIES = VERSION + MOVIES;
    public static final String BASE_PATH_MOVIES_WITH = BASE_PATH_MOVIES + "/";
    public static final String ID = "/{id}";

    /**
     * Private constructor for utility class.
     */
    private Paths() {

    }
}
