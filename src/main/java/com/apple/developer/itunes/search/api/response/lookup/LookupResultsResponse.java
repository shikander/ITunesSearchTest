package com.apple.developer.itunes.search.api.response.lookup;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LookupResultsResponse {

    public int resultCount;
    public ArrayList<Result> results;
}
