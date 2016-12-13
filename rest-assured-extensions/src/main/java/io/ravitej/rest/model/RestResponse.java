package io.ravitej.rest.model;

import io.restassured.response.Response;

public class RestResponse<T> {

    private final Response response;
    private final T data;

    public RestResponse(final Response response, final T data) {
        this.response = response;
        this.data = data;
    }

    public T data() {
        return data;
    }

    public int statusCode() {
        return response.statusCode();
    }

    public String body() {
        return response.asString();
    }

    public boolean failed() {
        return !ok();
    }

    public boolean ok() {
        return response.statusCode() < 300;
    }
}