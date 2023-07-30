package com.apple.developer.itunes.search.api.request;

import lombok.Data;

@Data
public class ITunesSearch {

    private String term;

    private String limit;

    private String entity;
    private String country;
}
