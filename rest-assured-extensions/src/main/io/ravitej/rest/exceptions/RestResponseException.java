package io.ravitej.rest.exceptions;

import io.ravitej.rest.model.RestResponse;

public class RestResponseException extends RuntimeException {

    public RestResponseException(String message) {
        super(message);
    }

    public static <T> void throwOnResponseFailure(final RestResponse<T> response) {
        if (response.failed()) {
            throw new RestResponseException(String.format("Unexpected response status: %s", response.statusCode()));
        }
    }
}
