package examples.gjames.com.musicapp.dal.apis.itunes.models;

public class ItunesSearchResultsList {

    private int resultsCount;
    private ItunesSearchResult[] results;

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
