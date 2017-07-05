package examples.gjames.com.musicapp.activities.start;

import examples.gjames.com.musicapp.activities.MusicAppActivity;
import examples.gjames.com.musicapp.activities.controllers.ItunesSearchController;
import examples.gjames.com.musicapp.activities.controllers.MusicAppController;

public class ItunesSearchActivity extends MusicAppActivity {

    @Override
    protected MusicAppController createController() {
        return new ItunesSearchController();
    }
}
