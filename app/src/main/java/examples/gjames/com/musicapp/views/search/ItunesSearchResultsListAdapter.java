package examples.gjames.com.musicapp.views.search;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import examples.gjames.com.musicapp._dal.DownloadImageTask;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;

import static examples.gjames.com.musicapp.R.id;
import static examples.gjames.com.musicapp.R.layout;


public class ItunesSearchResultsListAdapter extends BaseAdapter implements DownloadImageTask.DownloadImageListener {

    private ItunesSearchResult[] results = new ItunesSearchResult[]{};
    private LayoutInflater layoutInflater;
    private Resources resources;
    private Map<String, Bitmap> urlImageMap = new HashMap<>();
    private Map<String, Boolean> startedDownload = new HashMap<>();

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
        ((TextView) view.findViewById(id.result_item_tv_album_name)).setText(item.getCollectionName());
        ((TextView) view.findViewById(id.result_item_tv_artist_name)).setText(item.getArtistName());
        ((TextView) view.findViewById(id.result_item_tv_track_name)).setText(item.getTrackName());
        String url = getArtworkUrl(i);
        if (urlImageMap.get(url) != null) {
            ((ImageView) view.findViewById(id.result_item_iv_album_image)).setImageBitmap(urlImageMap.get(i));
        } else if (!startedDownload.get(url)) {
            new DownloadImageTask(this).execute(url);
            startedDownload.put(url, true);
        }
        return view;
    }

    public void setResults(ItunesSearchResult[] results) {
        this.results = results;
        for (int i = 0; i < results.length; i++) {
            String url = getArtworkUrl(i);
            urlImageMap.put(url, null);
            startedDownload.put(url, false);
        }
        notifyDataSetChanged();
    }

    private String getArtworkUrl(int i) {
        return getItem(i).getArtworkUrl100();
    }

    @Override
    public void onDownloaded(String url, Bitmap bitmap) {
        urlImageMap.put(url, bitmap);
        notifyDataSetChanged();
    }
}
