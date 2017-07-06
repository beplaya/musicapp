package examples.gjames.com.musicapp.activities.search;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers.search.ItunesSearchController;
import examples.gjames.com.musicapp.controllers._MusicAppController;

public class ItunesSearchActivity extends _MusicAppActivity {

    @Override
    protected _MusicAppController createController() {
        return new ItunesSearchController();
    }
}
