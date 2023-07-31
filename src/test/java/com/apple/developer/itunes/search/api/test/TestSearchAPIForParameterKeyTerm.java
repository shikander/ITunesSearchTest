package com.apple.developer.itunes.search.api.test;

import com.apple.developer.itunes.search.api.model.ITunesSearchModel;
import com.apple.developer.itunes.search.api.request.search.ITunesSearch;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.apple.developer.itunes.search.util.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class TestSearchAPIForParameterKeyTerm {

    ITunesSearchModel searchModel = new ITunesSearchModel();

    @DataProvider(name = "iTunesSearchData")
    private Iterator<ITunesSearch> iTunesSearchIterator() throws IOException {
        String FILE_NAME = "SearchTermData";
        String SHEET_NAME = "SEARCH_TERM";
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap(FILE_NAME, SHEET_NAME);
        List<ITunesSearch> iTunesSearches = new ArrayList<>();
        for(LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            ITunesSearch iTunesSearch = new ITunesSearch();
            iTunesSearch.setTerm(data.get("term"));
            iTunesSearch.setLimit(data.get("limit"));
            iTunesSearch.setEntity(data.get("entity"));
            iTunesSearch.setCountry(data.get("country"));
            iTunesSearch.setMedia(data.get("media"));
            iTunesSearch.setLang(data.get("lang"));
            iTunesSearches.add(iTunesSearch);
        }
        return iTunesSearches.iterator();
    }

    @Test(dataProvider = "iTunesSearchData")
    public void testSearchWithDifferentParams(ITunesSearch iTunesSearch) throws IOException {
        Map<String, Object> searchTermsMap =  new HashMap<>();
        searchTermsMap.put("term", iTunesSearch.getTerm());
        searchTermsMap.put("limit", iTunesSearch.getLimit());
        searchTermsMap.put("entity", iTunesSearch.getEntity());
        searchTermsMap.put("country", iTunesSearch.getCountry());
        searchTermsMap.put("media", iTunesSearch.getMedia());
        searchTermsMap.put("lang", iTunesSearch.getLang());

        SearchResultsResponse searchResponse = searchModel.getSearchResultsByTerm(searchTermsMap);
        searchModel.validateResultsCount(searchResponse);
        searchModel.validateWrapperTypeInResponse(searchResponse);
        searchModel.validateKindResultKeyInResponse(searchResponse);
    }
}
