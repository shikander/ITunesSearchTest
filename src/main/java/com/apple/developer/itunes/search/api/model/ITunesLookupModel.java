package com.apple.developer.itunes.search.api.model;

import com.apple.developer.itunes.search.api.WebServiceCall;
import com.apple.developer.itunes.search.api.constants.EndPoints;
import com.apple.developer.itunes.search.api.response.lookup.LookupResultsResponse;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.apple.developer.itunes.search.util.CommonUtils;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Map;

public class ITunesLookupModel {

    private static String lookupResultsData = "src/test/resources/testdata/LookupExpectedData.yaml";

    public LookupResultsResponse getLookupResultsByTerm(Map<String, Object> queryParams){
        Response response = WebServiceCall.doGetCallWithQueryParams(EndPoints.lookupBaseUri, queryParams);
        JSONObject responseJSONObject = new JSONObject(response.jsonPath().get());
        LookupResultsResponse lookupResultsResponse = new Gson().fromJson(responseJSONObject.toString(), LookupResultsResponse.class);
        return lookupResultsResponse;
    }

    public void validateLookupResultsCount(LookupResultsResponse lookupResultsResponse){
        Assert.assertTrue(lookupResultsResponse != null);
        Assert.assertTrue(lookupResultsResponse.getResultCount() == lookupResultsResponse.getResults().size());
    }

    public void validateWrapperTypeInResponse(LookupResultsResponse resultsResponse){
        String wrapperType = CommonUtils.getDataFromYamlFile(lookupResultsData, "WrapperType");
        if(resultsResponse.getResults().size() > 0){
            for (int i=0; i< resultsResponse.getResults().size(); i++){
                if(resultsResponse.getResults().get(i).getWrapperType() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(resultsResponse.getResults().get(i).getWrapperType(), wrapperType)==0);
                else
                    Assert.assertFalse( "WrapperType " + wrapperType +" is not available for this search ", false);
            }
        }
        else {
            Assert.assertTrue(resultsResponse.getResults().size() == 0);
        }
    }

    public void validateExplicitnessInResponse(LookupResultsResponse resultsResponse){
        String explicitness = CommonUtils.getDataFromYamlFile(lookupResultsData, "Explicitness");
        if(resultsResponse.getResults().size() > 0){
            for (int i=0; i< resultsResponse.getResults().size(); i++){
                if(resultsResponse.getResults().get(i).getCollectionExplicitness() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(resultsResponse.getResults().get(i).getCollectionExplicitness(), explicitness)==0);
                else
                    Assert.assertFalse( "explicitnessA " + explicitness +" is not available for this search ", false);
            }
        }
        else {
            Assert.assertTrue(resultsResponse.getResults().size() == 0);
        }
    }

    public void validateArtistNameInResponse(LookupResultsResponse resultsResponse) {
        String artistNames = CommonUtils.getDataFromYamlFile(lookupResultsData, "ArtistNames");
        if (resultsResponse.getResults().size() > 0) {
            for (int i = 0; i < resultsResponse.getResults().size(); i++) {
                if (resultsResponse.getResults().get(i).getArtistName() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(resultsResponse.getResults().get(i).getArtistName(), artistNames) == 0);
                else
                    Assert.assertFalse("explicitnessA " + artistNames + " is not available for this search ", false);
            }
        } else {
            Assert.assertTrue(resultsResponse.getResults().size() == 0);
        }
    }

    public void validateCollectionNameInResponse(LookupResultsResponse resultsResponse){
        String collectionNames = CommonUtils.getDataFromYamlFile(lookupResultsData, "CollectionNames");
        if (resultsResponse.getResults().size() > 0) {
            for (int i = 0; i < resultsResponse.getResults().size(); i++) {
                if (resultsResponse.getResults().get(i).getCollectionName() != null)
                    Assert.assertTrue(StringUtils.indexOfAny(resultsResponse.getResults().get(i).getCollectionName(), collectionNames) == 0);
                else
                    Assert.assertFalse("explicitnessA " + collectionNames + " is not available for this search ", false);
            }
        }else {
            Assert.assertTrue(resultsResponse.getResults().size() == 0);
        }
    }
}
