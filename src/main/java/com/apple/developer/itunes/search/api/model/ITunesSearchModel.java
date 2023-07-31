package com.apple.developer.itunes.search.api.model;

import com.apple.developer.itunes.search.api.WebServiceCall;
import com.apple.developer.itunes.search.api.constants.EndPoints;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.apple.developer.itunes.search.util.CommonUtils;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Map;

public class ITunesSearchModel {

    private static String searchResultsData = "src/test/resources/testdata/SearchExpectedData.yaml";
    private static int searchLimitCount = 51;

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

    public void validateResultsCount(SearchResultsResponse searchResponse){
        Assert.assertTrue(searchResponse.getResultCount() < searchLimitCount);
        Assert.assertTrue(searchResponse.getResults().size() < searchLimitCount);
        Assert.assertTrue(searchResponse.getResultCount() == searchResponse.getResults().size());
    }

    public void validateWrapperTypeInResponse(SearchResultsResponse searchResponse){
        String wrapperType = CommonUtils.getDataFromYamlFile(searchResultsData, "WrapperType");
        if(searchResponse.getResults().size() > 0){
            for (int i=0; i< searchResponse.getResults().size(); i++){
                if(searchResponse.getResults().get(i).getWrapperType() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(searchResponse.getResults().get(i).getWrapperType(), wrapperType)==0);
                else
                    Assert.assertFalse( "WrapperType " + wrapperType +" is not available for this search ", false);
            }
        }
        else {
            Assert.assertTrue(searchResponse.getResults().size() == 0);
        }
    }

    public void validateKindResultKeyInResponse(SearchResultsResponse searchResponse){
        String kind = CommonUtils.getDataFromYamlFile(searchResultsData, "Kind");
        if(searchResponse.getResults().size() > 0){
            for (int i=0; i< searchResponse.getResults().size(); i++){
                if(searchResponse.getResults().get(i).getKind() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(searchResponse.getResults().get(i).getKind(), kind)==0);
                else
                    Assert.assertFalse( "Kind result " + kind + " is not available for this search ", false);
            }
        }else {
            Assert.assertTrue(searchResponse.getResults().size() == 0);
        }
    }


}
