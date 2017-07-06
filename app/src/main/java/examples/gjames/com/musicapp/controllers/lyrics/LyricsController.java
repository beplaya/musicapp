package examples.gjames.com.musicapp.controllers.lyrics;

import android.util.Log;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers.ItunesSearchResultTransport;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.views.lyrics.LyricsView;
import james.a.grant.wagon.Wagon;

public class LyricsController extends _MusicAppController<LyricsView> {
    public ItunesSearchResultTransport transport = new ItunesSearchResultTransport();

    @Override
    public void init(_MusicAppActivity activity) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super.init(activity);
        new Wagon<ItunesSearchResultTransport>(transport.getClass(), transport).unpack(activity.getIntent());
        getView().update(transport.selectedItunesSearchResult);
        Log.i("sad","asd");
    }
}
