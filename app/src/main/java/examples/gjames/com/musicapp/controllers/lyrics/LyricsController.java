package examples.gjames.com.musicapp.controllers.lyrics;

import android.util.Log;

import examples.gjames.com.musicapp._dal.apis.lyricswikia.LyricsSearcher;
import examples.gjames.com.musicapp._dal.apis.lyricswikia.models.Lyrics;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers.ItunesSearchResultTransport;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.views.lyrics.LyricsView;
import james.a.grant.wagon.Wagon;

public class LyricsController extends _MusicAppController<LyricsView> implements LyricsSearcher.LyricsSearcherListener {
    public ItunesSearchResultTransport transport = new ItunesSearchResultTransport();
    private LyricsSearcher lyricsSearcher;

    @Override
    public void init(_MusicAppActivity activity) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super.init(activity);
        new Wagon<ItunesSearchResultTransport>(transport.getClass(), transport).unpack(activity.getIntent());
        getView().update(transport.selectedItunesSearchResult);

        lyricsSearcher  = new LyricsSearcher(this);
        lyricsSearcher.search(transport.selectedItunesSearchResult);
    }

    @Override
    public void onLyricsResults(Lyrics lyrics, boolean error) {

    }
}
