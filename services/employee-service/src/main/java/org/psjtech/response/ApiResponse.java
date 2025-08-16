package org.psjtech.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.psjtech.enums.Status;

import java.io.Serial;
import java.io.Serializable;

/**
 * Standardized wrapper for all REST API responses.
 * Ensures consistent response structure across success and error cases.
 *
 * <p>Typical usage:</p>
 * <pre>
 *     // Success without data
 *     return ApiResponse.success(HttpStatus.OK.value(), "Operation successful");
 *
 *     // Success with data
 *     return ApiResponse.success(HttpStatus.OK.value(), "Employee fetched", employee);
 *
 *     // Error
 *     return ApiResponse.error(new ErrorResponse(...));
 * </pre>
 *
 * @param <T> The type of the response payload (if any)
 */
@JsonPropertyOrder({ "statusCode", "message", "status", "data", "error" })
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields in JSON
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
public final class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Integer statusCode;
    private final String message;
    private final Status status;
    private final ErrorResponse error;
    private final T data;

    @JsonCreator
    public ApiResponse(
            @JsonProperty("statusCode") Integer statusCode,
            @JsonProperty("message") String message,
            @JsonProperty("status") Status status,
            @JsonProperty("error") ErrorResponse error,
            @JsonProperty("data") T data
    ) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
        this.error = error;
        this.data = data;
    }

    // ---------- Static Factory Methods ----------

    /**
     * Create a success response with only status and message.
     */
    public static <T> ApiResponse<T> success(Integer statusCode, String message) {
        return new ApiResponse<>(statusCode, message, Status.SUCCESS, null, null);
    }

    /**
     * Create an accepted response (for async or background jobs).
     */
    public static <T> ApiResponse<T> accepted(Integer statusCode, String message) {
        return new ApiResponse<>(statusCode, message, Status.ACCEPTED, null, null);
    }

    /**
     * Create a success response with data.
     */
    public static <T> ApiResponse<T> success(Integer statusCode, String message, T data) {
        return new ApiResponse<>(statusCode, message, Status.SUCCESS, null, data);
    }

    /**
     * Create a generic success response (no message, no data).
     */
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(null, null, Status.SUCCESS, null, null);
    }

    /**
     * Create an error response with details.
     */
    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return new ApiResponse<>(error.statusCode(), error.message(), Status.ERROR, error, null);
    }
}