package com.apple.developer.itunes.search.api.response.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchApiResponse {

    private Integer resultCount;
    private List<Result> results = new ArrayList<Result>();
}
