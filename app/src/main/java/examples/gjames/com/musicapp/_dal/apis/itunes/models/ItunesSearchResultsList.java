package examples.gjames.com.musicapp._dal.apis.itunes.models;

public class ItunesSearchResultsList {

    private int resultsCount;
    private ItunesSearchResult[] results = new ItunesSearchResult[]{};

    public ItunesSearchResult[] getResults() {
        return results;
    }

    public void setResults(ItunesSearchResult[] results) {
        this.results = results;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }
}
