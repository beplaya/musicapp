package examples.gjames.com.musicapp.controllers.search;

import android.view.View;

import examples.gjames.com.musicapp._dal.apis.itunes.ItunesSearcher;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResultsList;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.views.search.ItunesSearchView;

public class ItunesSearchController extends _MusicAppController<ItunesSearchView> implements ItunesSearcher.ItunesSearcherListener {

    private ItunesSearcher itunesSearcher;

    @Override
    public void init(_MusicAppActivity activity) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super.init(activity);
        itunesSearcher = new ItunesSearcher(this);
    }

    public View.OnClickListener handleSearch() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  terms = getView().getTerms();
                if(terms.isEmpty()){
                    getView().onInvalidSearchTerms();
                }else {
                    itunesSearcher.search(terms);
                }
            }
        };
    }

    @Override
    public void onSearchResults(ItunesSearchResultsList list, boolean error) {
        if (error) {
            getView().onSearchError();
        } else {
            getView().onSearchResults(list.getResults());
        }
    }
}
