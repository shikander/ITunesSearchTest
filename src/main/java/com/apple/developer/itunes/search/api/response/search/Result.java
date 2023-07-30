package com.apple.developer.itunes.search.api.response.search;

import lombok.Data;

@Data
public class Result {

    private String wrapperType;
    private String kind;
    private Integer collectionId;
    private String trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private Integer collectionArtistId;
    private String collectionArtistViewUrl;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String previewUrl;
    private String artworkUrl30;
    private String artworkUrl60;
    private String artworkUrl100;
    private Double collectionPrice;
    private Double trackPrice;
    private Double trackRentalPrice;
    private Double collectionHdPrice;
    private Double trackHdPrice;
    private Double trackHdRentalPrice;
    private String releaseDate;
    private String collectionExplicitness;
    private String trackExplicitness;
    private Integer discCount;
    private Integer discNumber;
    private Integer trackCount;
    private Integer trackNumber;
    private Integer trackTimeMillis;
    private String country;
    private String currency;
    private String primaryGenreName;
    private String contentAdvisoryRating;
    private String longDescription;
    private Boolean hasITunesExtras;
    private String shortDescription;
    private Integer artistId;
    private String artistViewUrl;
    private Boolean isStreamable;
}
