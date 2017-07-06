package examples.gjames.com.musicapp.controllers.search;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.views.search.ItunesSearchView;
import examples.gjames.com.musicapp.views._MusicAppView;

public class ItunesSearchController extends _MusicAppController {

    @Override
    public void init(_MusicAppActivity activity) {
        super.init(activity);

    }

    @Override
    protected _MusicAppView createView() {
        return new ItunesSearchView();
    }
}
