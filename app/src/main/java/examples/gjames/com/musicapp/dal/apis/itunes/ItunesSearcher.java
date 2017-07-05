package examples.gjames.com.musicapp.dal.apis.itunes;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

import examples.gjames.com.musicapp.dal.StringRequestTask;
import examples.gjames.com.musicapp.dal.apis.itunes.models.ItunesSearchResultsList;

public class ItunesSearcher implements StringRequestTask.StringRequestTaskListener {
    private ItunesSearcherListener listener;
    private String baseURL = "https://itunes.apple.com/search?term=";
    private Gson gson = new Gson();

    public interface ItunesSearcherListener {
        void onSearchResults(ItunesSearchResultsList list);
    }

    public ItunesSearcher(ItunesSearcherListener listener) {
        this.listener = listener;
    }

    public void search(String... terms) {
        try {
            new StringRequestTask(this).execute(new URL(String.format("%s%s", baseURL, TextUtils.join("+", terms))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onRequestComplete(null);
        }
    }

    @Override
    public void onRequestComplete(String requestResponse) {
        ItunesSearchResultsList resultsList = parse(requestResponse);
        listener.onSearchResults(resultsList == null ? new ItunesSearchResultsList() : resultsList);
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
