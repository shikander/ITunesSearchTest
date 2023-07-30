package com.apple.developer.itunes.search.api.model;

import com.apple.developer.itunes.search.api.WebServiceCall;
import com.apple.developer.itunes.search.api.constants.EndPoints;
import com.apple.developer.itunes.search.api.response.lookup.LookupResultsResponse;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.Map;

public class ITunesLookupModel {

    public LookupResultsResponse getLookupResultsByTerm(Map<String, Object> queryParams){
        Response response = WebServiceCall.doGetCallWithQueryParams(EndPoints.lookupBaseUri, queryParams);
        JSONObject responseJSONObject = new JSONObject(response.jsonPath().get());
        LookupResultsResponse lookupResultsResponse = new Gson().fromJson(responseJSONObject.toString(), LookupResultsResponse.class);
        return lookupResultsResponse;
    }

    public boolean validateWrapperTypeInResponse(LookupResultsResponse resultsResponse){
        String[] wrapperTypeArr = new String[]{"artist", "collection", "software", "track"};
        Boolean isWrapperSame = false;
        for (int i=0; i< resultsResponse.getResults().size(); i++){
            if(resultsResponse.getResults().get(i).getWrapperType() != null)
                isWrapperSame = StringUtils.indexOfAny(resultsResponse.getResults().get(i).getWrapperType(), wrapperTypeArr)==0;
        }
        return isWrapperSame;
    }
}
