package examples.gjames.com.musicapp.controllers;

import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;
import james.a.grant.wagon.Crate;

public class ItunesSearchResultTransport {

    @Crate(key = "itunesSearchResult")
    public ItunesSearchResult selectedItunesSearchResult = new ItunesSearchResult();

}
