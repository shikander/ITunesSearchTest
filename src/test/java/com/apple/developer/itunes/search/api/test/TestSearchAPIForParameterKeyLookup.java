package com.apple.developer.itunes.search.api.test;

import com.apple.developer.itunes.search.api.model.ITunesLookupModel;
import com.apple.developer.itunes.search.api.request.lookup.ITunesLookup;
import com.apple.developer.itunes.search.api.response.lookup.LookupResultsResponse;
import com.apple.developer.itunes.search.api.response.search.SearchResultsResponse;
import com.apple.developer.itunes.search.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TestSearchAPIForParameterKeyLookup {

    ITunesLookupModel lookupModel = new ITunesLookupModel();

    @DataProvider(name = "iTunesLookupData")
    private Iterator<ITunesLookup> iTunesLookupIterator() throws IOException {
        String FILE_NAME = "SearchTermData";
        String SHEET_NAME = "LOOKUP_TERM";
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap(FILE_NAME, SHEET_NAME);
        List<ITunesLookup> iTunesLookupsList = new ArrayList<>();
        for(LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            ITunesLookup iTunesLookup = new ITunesLookup();
            iTunesLookup.setId(data.get("id"));
            iTunesLookup.setAmgArtistId(data.get("amgArtistId"));
            iTunesLookup.setEntity(data.get("entity"));
            iTunesLookup.setLimit(data.get("limit"));
            iTunesLookup.setSort(data.get("sort"));
            iTunesLookup.setUpc(data.get("upc"));
            iTunesLookup.setAmgAlbumId(data.get("amgAlbumId"));
            iTunesLookup.setAmgVideoId(data.get("amgVideoId"));
            iTunesLookup.setIsbn(data.get("isbn"));
            iTunesLookupsList.add(iTunesLookup);
        }
        return iTunesLookupsList.iterator();
    }

    @Test(dataProvider = "iTunesLookupData")
    public void testLookupWithDifferentParams(ITunesLookup iTunesLookup) throws IOException {
        Map<String, Object> lookupTermsMap =  new HashMap<>();
        lookupTermsMap.put("id", iTunesLookup.getId());
        lookupTermsMap.put("amgArtistId", iTunesLookup.getAmgArtistId());
        lookupTermsMap.put("entity", iTunesLookup.getEntity());
        lookupTermsMap.put("limit", iTunesLookup.getLimit());
        lookupTermsMap.put("sort", iTunesLookup.getSort());
        lookupTermsMap.put("upc", iTunesLookup.getUpc());
        lookupTermsMap.put("amgAlbumId", iTunesLookup.getAmgAlbumId());
        lookupTermsMap.put("amgVideoId", iTunesLookup.getAmgVideoId());
        lookupTermsMap.put("isbn", iTunesLookup.getIsbn());

        LookupResultsResponse lookupResultsResponse = lookupModel.getLookupResultsByTerm(lookupTermsMap);
        lookupModel.validateLookupResultsCount(lookupResultsResponse);
        lookupModel.validateWrapperTypeInResponse(lookupResultsResponse);
        lookupModel.validateExplicitnessInResponse(lookupResultsResponse);
        lookupModel.validateArtistNameInResponse(lookupResultsResponse);
    }
}
