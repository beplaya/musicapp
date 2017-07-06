package examples.gjames.com.musicapp.views.search;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.controllers.search.ItunesSearchController;
import examples.gjames.com.musicapp.views._MusicAppView;

import static examples.gjames.com.musicapp.R.id;
import static examples.gjames.com.musicapp.R.layout;

public class ItunesSearchView extends _MusicAppView<ItunesSearchController> {

    private EditText etSearchTerms;
    private Button btnSearch;
    private ListView resultsList;
    private ItunesSearchResultsListAdapter itunesSearchResultsListAdapter;
    private TextView tvSearchErrorMsg;
    private View progressBar;

    @Override
    protected void bind(_MusicAppActivity activity, final _MusicAppController controller) {
        tvSearchErrorMsg = findTextView(id.tv_search_error_msg);
        etSearchTerms = findEditText(id.et_itunes_search_terms);
        btnSearch = findButton(id.btn_itunes_search);
        progressBar = findViewById(id.pb_search_itunes);
        hideProgressBar();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSearch(view);
            }
        });
        resultsList = findListView(id.lv_search_results);
        resultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getController().onSearchResultSelected(itunesSearchResultsListAdapter.getItem(i));
            }
        });
        itunesSearchResultsListAdapter = new ItunesSearchResultsListAdapter(activity.getLayoutInflater(), activity.getResources());
        resultsList.setAdapter(itunesSearchResultsListAdapter);
    }

    private void handleSearch(View view) {
        progressBar.setVisibility(View.VISIBLE);
        tvSearchErrorMsg.setText("");
        tvSearchErrorMsg.setVisibility(View.GONE);
        etSearchTerms.setFocusableInTouchMode(false);
        etSearchTerms.setFocusable(false);
        etSearchTerms.setFocusableInTouchMode(true);
        etSearchTerms.setFocusable(true);
        InputMethodManager imm = (InputMethodManager) ItunesSearchView.this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        getController().handleSearch();
    }

    public String getTerms() {
        return etSearchTerms.getText().toString().trim();
    }

    public void onInvalidSearchTerms() {
        showErrorMsg("Please enter valid search terms");
        reset();
        hideProgressBar();
    }

    public void onSearchError() {
        showErrorMsg("There was an error with your search");
        reset();
        hideProgressBar();
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showErrorMsg(String s) {
        tvSearchErrorMsg.setVisibility(View.VISIBLE);
        tvSearchErrorMsg.setText(s);
    }

    private void reset() {
        etSearchTerms.setText("");
    }

    public void onSearchResults(ItunesSearchResult[] results) {
        tvSearchErrorMsg.setVisibility(View.GONE);
        itunesSearchResultsListAdapter.setResults(results);
        hideProgressBar();
        if (results.length == 0) {
            onNoResultsFound();
        }
    }

    private void onNoResultsFound() {
        tvSearchErrorMsg.setText("No results found");
        tvSearchErrorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return layout.activity_search;
    }


}
