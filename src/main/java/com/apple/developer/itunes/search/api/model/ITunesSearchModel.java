package com.apple.developer.itunes.search.api.model;

import com.apple.developer.itunes.search.api.WebServiceCall;
import com.apple.developer.itunes.search.api.constants.EndPoints;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ITunesSearchModel {

    public Response getSearchByTerm(Object value2){
        return WebServiceCall.doGetCallWithQueryParam(EndPoints.searchBaseUri, "term", value2);
    }
    public Response getSearchByTerm(Map<String, Object> queryParams){
        return WebServiceCall.doGetCallWithQueryParams(EndPoints.searchBaseUri, queryParams);
    }

    public SearchResultsResponse getSearchResultsByTerm(Map<String, Object> queryParams){
        Response response = WebServiceCall.doGetCallWithQueryParams(EndPoints.searchBaseUri, queryParams);
        JSONObject responseJSONObject = new JSONObject(response.jsonPath().get());
        SearchResultsResponse searchResultsResponse = new Gson().fromJson(responseJSONObject.toString(), SearchResultsResponse.class);
        return searchResultsResponse;
    }

    public boolean validateWrapperTypeInResponse(SearchResultsResponse searchResponse){
        String[] wrapperTypeArr = new String[]{"track", "audiobook", "software"};
        Boolean isWrapperSame = false;
        for (int i=0; i< searchResponse.getResults().size(); i++){
            if(searchResponse.getResults().get(i).getWrapperType() != null)
                isWrapperSame = StringUtils.indexOfAny(searchResponse.getResults().get(i).getWrapperType(), wrapperTypeArr)==0;
        }
        return isWrapperSame;
    }

    public boolean validateArtistNameInResponse(SearchResultsResponse searchResponse){
        String[] artistNameArr = new String[]{"Jack Johnson"};
        Boolean isWrapperSame = false;
        for (int i=0; i< searchResponse.getResults().size(); i++){
            if(searchResponse.getResults().get(i).getWrapperType() != null)
                isWrapperSame = StringUtils.indexOfAny(searchResponse.getResults().get(i).getArtistName(), artistNameArr)==0;
        }
        return isWrapperSame;
    }
}
