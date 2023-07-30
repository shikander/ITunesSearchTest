package com.apple.developer.itunes.search.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class WebServiceCall {

    public static Response doGetCallWithQueryParam(String baseUri, String value1, Object value2){
        return RestAssured.given()
                .baseUri(baseUri)
                .queryParam(value1, value2).log().uri()
                .when().get().thenReturn();
    }
    public static Response doGetCallWithQueryParams(String baseUri, Map<String, Object> queryParams){
        return RestAssured.given()
                .baseUri(baseUri)
                .queryParams(queryParams).log().uri()
                .when().get().thenReturn();
    }
}
