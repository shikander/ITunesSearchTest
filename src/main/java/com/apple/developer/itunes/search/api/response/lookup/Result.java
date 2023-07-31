package com.apple.developer.itunes.search.api.response.lookup;

import lombok.Data;

@Data
public class Result {

    private String wrapperType;
    private String collectionType;
    private int artistId;
    private int collectionId;
    private int amgArtistId;
    private String artistName;
    private String collectionName;
    private String collectionCensoredName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private String collectionPrice;
    private String collectionExplicitness;
    private int trackCount;
    private String copyright;
    private String country;
    private String currency;
    private String releaseDate;
    private String primaryGenreName;
}
