package examples.gjames.com.musicapp._dal.apis.lyricswikia;

import android.text.TextUtils;

import examples.gjames.com.musicapp._dal.StringRequestTask;
import examples.gjames.com.musicapp._dal.apis.Searcher;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;
import examples.gjames.com.musicapp._dal.apis.lyricswikia.models.Lyrics;

public class LyricsSearcher extends Searcher implements StringRequestTask.StringRequestTaskListener {

    private final LyricsSearcherListener listener;

    public interface LyricsSearcherListener {
        void onLyricsResults(Lyrics lyrics, boolean error);
    }

    public LyricsSearcher(LyricsSearcherListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRequestComplete(String requestResponse, boolean error) {
        super.onRequestComplete(requestResponse, error);
        Lyrics lyrics = parse(requestResponse);
        listener.onLyricsResults(lyrics, error);
    }

    @Override
    public String getBaseUrl() {
        return "http://lyrics.wikia.com/api.php?func=getSong&artist=%s&song=%s&fmt=json";
    }

    @Override
    protected String getUrlWithTokens(String[] terms, Object obj) {
        ItunesSearchResult searchResult = (ItunesSearchResult) obj;
        String artistNameToken = TextUtils.join("+", cleaner.cleanTerms(searchResult.getArtistName()));
        String trackNameToken = TextUtils.join("+", cleaner.cleanTerms(searchResult.getTrackName()));
        return String.format(getBaseUrl(), artistNameToken, trackNameToken);
    }

    private Lyrics parse(String resultString) {
        try {
            resultString = resultString == null ? "{}" :  resultString.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
            resultString = resultString.replace("song = ", "");
            return gson.fromJson(resultString, Lyrics.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
