package examples.gjames.com.musicapp.activities.controllers;

import examples.gjames.com.musicapp.activities.views.ItunesSearchView;
import examples.gjames.com.musicapp.activities.views.MusicAppView;

public class ItunesSearchController extends MusicAppController {
    @Override
    protected MusicAppView createView() {
        return new ItunesSearchView();
    }
}
