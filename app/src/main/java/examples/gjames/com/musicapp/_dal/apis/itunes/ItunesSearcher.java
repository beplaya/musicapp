package examples.gjames.com.musicapp._dal.apis.itunes;

import android.text.TextUtils;

import com.google.gson.Gson;

import examples.gjames.com.musicapp._dal.apis.Searcher;
import examples.gjames.com.musicapp._dal.apis.TermsCleaner;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResultsList;

public class ItunesSearcher extends Searcher {
    private ItunesSearcherListener listener;
    private Gson gson = new Gson();
    private TermsCleaner cleaner = new TermsCleaner();

    @Override
    public String getBaseUrl() {
        return "https://itunes.apple.com/search?term=";
    }

    public interface ItunesSearcherListener {
        void onSearchResults(ItunesSearchResultsList list, boolean error);
    }

    public ItunesSearcher(ItunesSearcherListener listener) {
        this.listener = listener;
    }

    @Override
    protected String getUrlWithTokens(String[] terms, Object obj) {
        return String.format("%s%s", getBaseUrl(), generateTermsString(terms));
    }

    protected String generateTermsString(String[] terms) {
        return TextUtils.join("+", cleaner.cleanTerms(terms));
    }

    @Override
    public void onRequestComplete(String requestResponse, boolean error) {
        super.onRequestComplete(requestResponse, error);
        ItunesSearchResultsList resultsList = parse(requestResponse);
        ItunesSearchResultsList finalList = resultsList == null ? new ItunesSearchResultsList() : resultsList;
        finalList.sortByCollectionName();
        listener.onSearchResults(finalList, error);
    }

    private ItunesSearchResultsList parse(String resultString) {
        try {
            return gson.fromJson(resultString, ItunesSearchResultsList.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
