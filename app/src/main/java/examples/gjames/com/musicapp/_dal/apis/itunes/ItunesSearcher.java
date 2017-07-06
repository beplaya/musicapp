package examples.gjames.com.musicapp._dal.apis.itunes;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

import examples.gjames.com.musicapp._dal.StringRequestTask;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResultsList;

public class ItunesSearcher implements StringRequestTask.StringRequestTaskListener {
    private ItunesSearcherListener listener;
    private String baseURL = "https://itunes.apple.com/search?term=";
    private Gson gson = new Gson();
    private TermsCleaner cleaner = new TermsCleaner();

    public interface ItunesSearcherListener {
        void onSearchResults(ItunesSearchResultsList list, boolean error);
    }

    public ItunesSearcher(ItunesSearcherListener listener) {
        this.listener = listener;
    }

    public void search(String... terms) {
        try {
            new StringRequestTask(this).execute(new URL(String.format("%s%s", baseURL, generateTermsString(terms))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onRequestComplete(null, true);
        }
    }

    private String generateTermsString(String[] terms) {
        return TextUtils.join("+", cleaner.cleanTerms(terms));
    }

    @Override
    public void onRequestComplete(String requestResponse, boolean error) {
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
