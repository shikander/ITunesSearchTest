package com.apple.developer.itunes.search.api.model;

import com.apple.developer.itunes.search.api.WebServiceCall;
import com.apple.developer.itunes.search.api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.Map;

public class SearchModel {

    public Response getSearchByTerm(Object value2){
        return WebServiceCall.doGetCallWithQueryParam(EndPoints.searchBaseUri, "term", value2);
    }
    public Response getSearchByTerm(Map<String, Object> queryParams){
        return WebServiceCall.doGetCallWithQueryParams(EndPoints.searchBaseUri, queryParams);
    }
}
