package com.apple.developer.itunes.search.api.test;

import com.apple.developer.itunes.search.api.model.SearchModel;
import com.apple.developer.itunes.search.api.request.ITunesSearch;
import com.apple.developer.itunes.search.util.ExcelUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class TestSearchAPIForParameterKeyTerm {

    SearchModel searchModel = new SearchModel();

    @DataProvider(name = "searchTermsData")
    private Object[][] searchTermsData(){
        return new Object[][] {{"jack"}, {"johnson"}, {"jack+johnson"}};
    }

    @Test(dataProvider = "searchTermsData")
    public void testSearchWithTerm(String searchTerm){
        Response searchResponse = searchModel.getSearchByTerm(searchTerm);

        Assert.assertTrue(searchResponse.getStatusCode() == 200);
        Assert.assertEquals(Integer.parseInt(searchResponse.getBody().jsonPath().get("resultCount").toString()),
                searchResponse.getBody().jsonPath().getList("results").size());
    }

    @DataProvider(name = "iTunesSearchData")
    private Iterator<ITunesSearch> iTunesSearchIterator() throws IOException {
        String FILE_NAME = "SearchTermData";
        String SHEET_NAME = "SEARCH_TERM";
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap(FILE_NAME, SHEET_NAME);
        List<ITunesSearch> iTunesSearches = new ArrayList<>();
        for(LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            ITunesSearch iTunesSearch = new ITunesSearch();
            iTunesSearch.setTerm(data.get("term"));
            iTunesSearch.setEntity(data.get("entity"));
            iTunesSearch.setLimit(data.get("limit"));
            iTunesSearch.setCountry(data.get("country"));
            iTunesSearches.add(iTunesSearch);
        }
        return iTunesSearches.iterator();
    }

    @Test(dataProvider = "iTunesSearchData")
    public void testSearchWithDifferentParams(ITunesSearch iTunesSearch) throws IOException {
        Map<String, Object> searchTermsMap =  new HashMap<>();
        searchTermsMap.put("term", iTunesSearch.getTerm());
        searchTermsMap.put("entity", iTunesSearch.getEntity());
        searchTermsMap.put("limit", iTunesSearch.getLimit());
        searchTermsMap.put("country", iTunesSearch.getCountry());
        Response searchResponse = searchModel.getSearchByTerm(searchTermsMap);

        Assert.assertTrue(searchResponse.getStatusCode() == 200);
        Assert.assertEquals(Integer.parseInt(searchResponse.getBody().jsonPath().get("resultCount").toString()),
                searchResponse.getBody().jsonPath().getList("results").size());
    }
}
