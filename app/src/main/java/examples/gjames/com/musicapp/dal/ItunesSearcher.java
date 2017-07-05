package examples.gjames.com.musicapp.dal;

import android.net.Uri;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import examples.gjames.com.musicapp.dal.models.ItunesSearchResultsList;

public class ItunesSearcher {
    private String baseURL = "https://itunes.apple.com/search?term=";
    private Gson gson = new Gson();

    public void search(String... terms) throws MalformedURLException {
        new StringRequestTask().execute(new URL(String.format("%s%s", baseURL, TextUtils.join("+", terms))));
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
