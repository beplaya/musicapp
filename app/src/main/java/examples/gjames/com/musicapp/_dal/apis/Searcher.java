package examples.gjames.com.musicapp._dal.apis;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

import examples.gjames.com.musicapp._dal.StringRequestTask;

public abstract class Searcher implements StringRequestTask.StringRequestTaskListener {

    protected Gson gson = new Gson();
    protected TermsCleaner cleaner = new TermsCleaner();

    public abstract String getBaseUrl();

    public void searchByTerms(String... terms) {
        search(terms, null);
    }

    public void search(Object obj) {
        search(null, obj);
    }


    private void search(String[] terms, Object obj) {
        try {
            new StringRequestTask(this).execute(new URL(getUrlWithTokens(terms, obj)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onRequestComplete(null, true);
        }
    }

    protected abstract String getUrlWithTokens(String[] terms, Object obj);

    @Override
    public void onRequestComplete(String string, boolean error) {
    }

}
