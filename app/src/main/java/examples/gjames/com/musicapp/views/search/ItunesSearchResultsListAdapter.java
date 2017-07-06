package examples.gjames.com.musicapp.views.search;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import examples.gjames.com.musicapp.R;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;

import static examples.gjames.com.musicapp.R.*;


public class ItunesSearchResultsListAdapter extends BaseAdapter {

    private ItunesSearchResult[] results = new ItunesSearchResult[]{};
    private LayoutInflater layoutInflater;
    private Resources resources;

    public ItunesSearchResultsListAdapter(LayoutInflater layoutInflater, Resources resources) {
        this.layoutInflater = layoutInflater;
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return results.length;
    }

    @Override
    public ItunesSearchResult getItem(int i) {
        return results[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItunesSearchResult item = getItem(i);
        if (view == null) {
            view = layoutInflater.inflate(layout.itunes_search_result_item, null);
        }
        ((TextView)view.findViewById(id.result_item_tv_album_name)).setText(item.getCollectionName());
        ((TextView)view.findViewById(id.result_item_tv_artist_name)).setText(item.getArtistName());
        ((TextView)view.findViewById(id.result_item_tv_track_name)).setText(item.getTrackName());
        return view;
    }

    public void setResults(ItunesSearchResult[] results) {
        this.results = results;
        notifyDataSetChanged();
    }
}