package com.apple.developer.itunes.search.api.response.lookup;

import lombok.Data;

@Data
public class Result {

    private String wrapperType;
    private String artistType;
    private String artistName;
    private String artistLinkUrl;
    private String artistId;
    private String amgArtistId;
    private String primaryGenreName;
    private String primaryGenreId;
}
