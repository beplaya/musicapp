package examples.gjames.com.musicapp.views.search;

import android.widget.Button;
import android.widget.EditText;
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
    private TextView tvTesting;

    @Override
    protected void bind(_MusicAppActivity activity, _MusicAppController controller) {
        etSearchTerms = findEditText(id.et_itunes_search_terms);
        btnSearch = findButton(id.btn_itunes_search);
        tvTesting = findTextView(id.tv_testing);
        btnSearch.setOnClickListener(getController().handleSearch());
    }

    public String getTerms() {
        return etSearchTerms.getText().toString().trim();
    }

    @Override
    protected int getLayoutId() {
        return layout.activity_search;
    }


    public void onSearchError() {
        tvTesting.setText("Error!");
        reset();
    }

    private void reset() {
        etSearchTerms.setText("");
    }

    public void onSearchResults(ItunesSearchResult[] results) {
        String s = "";
        for (ItunesSearchResult result : results) {
            s += result.toString()+ "\n\n";
        }
        tvTesting.setText(s);
    }

    public void onInvalidSearchTerms() {
        tvTesting.setText("Invalid terms!");
        reset();
    }
}
