package org.psjtech.response;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * Standardized error response returned by all REST APIs.
 * Ensures consistent error reporting across validation,
 * business logic, authentication, and system-level errors.
 *
 * @param statusCode   HTTP status code (e.g., 400, 401, 500)
 * @param errorCode    Application or business-specific error code
 * @param message      Human-readable error message
 * @param requestId    Unique identifier for tracking the request (useful in distributed systems)
 * @param errors       List of specific error messages (e.g., validation issues)
 * @param errorDetails Additional structured error metadata (field errors, constraints, etc.)
 * @param timestamp    The time when the error occurred
 */
public record ErrorResponse(
        int statusCode,
        String errorCode,
        String message,
        String requestId,
        List<String> errors,
        Map<String, Object> errorDetails,
        OffsetDateTime timestamp
) {

    /**
     * Minimal error response (statusCode + message).
     */
    public ErrorResponse(int statusCode, String message) {
        this(statusCode, null, message, null, null, null, OffsetDateTime.now());
    }

    /**
     * Error response with statusCode, errorCode and message.
     */
    public ErrorResponse(int statusCode, String errorCode, String message) {
        this(statusCode, errorCode, message, null, null, null, OffsetDateTime.now());
    }

    /**
     * Error response with statusCode, message, and detailed errors.
     */
    public ErrorResponse(int statusCode, String errorCode, String message, List<String> errors) {
        this(statusCode, errorCode, message, null, errors, null, OffsetDateTime.now());
    }

    /**
     * Error response with statusCode, errorCode, message and errorDetails
     */
    public ErrorResponse(int statusCode, String errorCode, String message, Map<String, Object> errorDetails){
        this(statusCode, errorCode, message, null, null, errorDetails, OffsetDateTime.now());
    }

    /**
     * Full error response with all fields except timestamp (auto-filled).
     */
    public ErrorResponse(int statusCode,
                         String errorCode,
                         String message,
                         String requestId,
                         List<String> errors,
                         Map<String, Object> errorDetails) {
        this(statusCode, errorCode, message, requestId, errors, errorDetails, OffsetDateTime.now());
    }
}
