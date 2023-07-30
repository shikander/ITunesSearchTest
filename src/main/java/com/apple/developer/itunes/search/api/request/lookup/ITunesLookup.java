package com.apple.developer.itunes.search.api.request.lookup;

import lombok.Data;

@Data
public class ITunesLookup {

    private String id;
    private String amgArtistId;
    private String entity;
    private String limit;
    private String sort;
    private String upc;
    private String amgAlbumId;
    private String amgVideoId;
    private String isbn;
}
