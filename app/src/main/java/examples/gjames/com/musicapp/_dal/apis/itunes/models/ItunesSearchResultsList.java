package examples.gjames.com.musicapp._dal.apis.itunes.models;

import java.util.Arrays;
import java.util.Comparator;

public class ItunesSearchResultsList {

    private int resultsCount;
    private ItunesSearchResult[] results = new ItunesSearchResult[]{};

    public void sortByCollectionName() {
        Arrays.sort(results, new Comparator<ItunesSearchResult>() {
            @Override
            public int compare(ItunesSearchResult a, ItunesSearchResult b) {
                if (a == null || a.getCollectionName() == null) {
                    return -1;
                } else if (b == null || b.getCollectionName() == null) {
                    return 1;
                }
                return a.getCollectionName().compareTo(b.getCollectionName());
            }
        });
    }

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
