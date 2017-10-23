package org.fundacionjala.at04.moviesrestapi.constant;

import java.time.Instant;

/**
 * Error responses utility class.
 */
public final class ErrorResponse {

    /**
     * Private constructor for utility class.
     */
    private ErrorResponse() {

    }

    /**
     * This method generate a Json error body.
     *
     * @param code    error status code.
     * @param message error message.
     * @return the json string representation.
     */
    public static String buildBody(Integer code, String message) {
        long unixTimestamp = Instant.now().getEpochSecond();
        return String.format("{\"timestamp\":%d,\"code\":%d,\"message\":\"%s\"}", unixTimestamp, code, message);
    }
}
