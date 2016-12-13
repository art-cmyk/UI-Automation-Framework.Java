package io.ravitej.rest.tools;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestfulServicesTestTool {

    private final RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public RestfulServicesTestTool() {
        this(RestAssured.baseURI);
    }

    public RestfulServicesTestTool(final String baseURI) {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    public RequestSpecification requestSpecification() {
        return requestSpecification;
    }
}
