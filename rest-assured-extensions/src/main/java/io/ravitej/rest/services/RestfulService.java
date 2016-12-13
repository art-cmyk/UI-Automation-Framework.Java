package io.ravitej.rest.services;

import io.ravitej.rest.tools.RestfulServicesTestTool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class RestfulService {

    protected final RestfulServicesTestTool tool;

    public RestfulService(final RestfulServicesTestTool tool) {
        this.tool = tool;
    }

    protected RestfulService accept(ContentType contentType) {
        tool.requestSpecification().accept(contentType);
        return this;
    }

    protected Response post(final String path) {
        return post(path, null);
    }

    protected Response post(final String path, final String payload) {
        return given().
                spec(tool.requestSpecification()).
                body(payload == null ? "" : payload).
                post(path).thenReturn();
    }

    protected Response get(final String path) {
        return get(path, null);
    }

    protected Response get(final String path, final String queryParam) {
        return given().
                spec(tool.requestSpecification()).
                queryParam(queryParam == null ? "" : queryParam).
                get(path).thenReturn();
    }

    protected Response put(final String path, final String payload) {
        return given().
                spec(tool.requestSpecification()).
                body(payload).
                put(path).thenReturn();
    }
}
