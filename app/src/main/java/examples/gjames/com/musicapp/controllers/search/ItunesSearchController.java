package examples.gjames.com.musicapp.controllers.search;

import android.content.Intent;
import android.util.Log;

import examples.gjames.com.musicapp._dal.apis.itunes.ItunesSearcher;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResultsList;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.activities.lyrics.LyricsActivity;
import examples.gjames.com.musicapp.controllers.ItunesSearchResultTransport;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.views.search.ItunesSearchView;
import james.a.grant.wagon.Wagon;

public class ItunesSearchController extends _MusicAppController<ItunesSearchView> implements ItunesSearcher.ItunesSearcherListener {

    private ItunesSearcher itunesSearcher;
    public ItunesSearchResultTransport transport = new ItunesSearchResultTransport();

    @Override
    public void init(_MusicAppActivity activity) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super.init(activity);
        itunesSearcher = new ItunesSearcher(this);
    }

    public void handleSearch() {
        String terms = getView().getTerms();
        if (terms.isEmpty()) {
            getView().onInvalidSearchTerms();
        } else {
            itunesSearcher.search(terms);
        }
    }

    @Override
    public void onSearchResults(ItunesSearchResultsList list, boolean error) {
        if (error) {
            getView().onSearchError();
        } else {
            getView().onSearchResults(list.getResults());
        }
    }

    public void onSearchResultSelected(ItunesSearchResult item) {
        transport.selectedItunesSearchResult = item;
        startNextAcitivity();
    }

    private void startNextAcitivity() {
        Intent intent = new Intent(activity.getApplicationContext(), LyricsActivity.class);
        boolean itWorked = new Wagon<ItunesSearchResultTransport>(transport.getClass(), transport).pack(intent);

        Log.i("", "pack: "+itWorked);
        Log.i("", intent.getExtras().toString());
        activity.startActivity(intent);
    }
}
