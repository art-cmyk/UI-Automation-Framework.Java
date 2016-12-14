package io.ravitej.rest.services;

import io.ravitej.rest.tools.RestfulServicesTestTool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

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

    protected RestfulService contentType(ContentType contentType) {
        tool.requestSpecification().contentType(contentType);
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
        return given().
                spec(tool.requestSpecification()).
                get(path).thenReturn();
    }

    protected Response get(final String path, final String... queryParams) {
        for (String s : queryParams) {
            addParams(ParamType.Query, s, null);
        }
        return given().
                spec(tool.requestSpecification()).
                get(path).thenReturn();
    }

    protected Response get(final String path, final Map queryParams) {
        addParams(ParamType.Query, queryParams);
        return given().
                spec(tool.requestSpecification()).
                get(path).thenReturn();
    }

    protected Response put(final String path, final String payload) {
        return given().
                spec(tool.requestSpecification()).
                body(payload).
                put(path).thenReturn();
    }

    private RequestSpecification addParams(ParamType paramType, String param, String paramValue){
        switch(paramType){
            case Form:{
                if (paramValue == null)
                    tool.requestSpecification().formParam(param);
                else
                    tool.requestSpecification().formParam(param, paramValue);
                break;
            }
            case Path:{
                if (paramValue == null)
                    throw new IllegalArgumentException("Form param value cannot be null");
                else
                    tool.requestSpecification().pathParam(param, paramValue);
                break;
            }
            case Query:{
                if (paramValue == null)
                    tool.requestSpecification().queryParam(param);
                else
                    tool.requestSpecification().queryParam(param, paramValue);
                break;
            }
        }
        return tool.requestSpecification();
    }

    private RequestSpecification addParams(ParamType paramType, Map<String, ?> params){
        switch(paramType){
            case Form:{
                tool.requestSpecification().formParams(params);
                break;
            }
            case Path:{
                tool.requestSpecification().pathParams(params);
                break;
            }
            case Query:{
                tool.requestSpecification().queryParams(params);
                break;
            }
        }
        return tool.requestSpecification();
    }


}
