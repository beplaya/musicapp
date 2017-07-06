package examples.gjames.com.musicapp.views.search;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;
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
        ImageView ivAlbumArt = view.findViewById(id.result_item_iv_album_image);
        String url = getArtworkUrl(i);
        if (urlImageMap.get(url) != null) {
            ivAlbumArt.setImageBitmap(urlImageMap.get(url));
        }
        return view;
    }

    public void setResults(ItunesSearchResult[] results) {
        this.results = results;
        for (int i = 0; i < results.length; i++) {
            String url = getArtworkUrl(i);
            urlImageMap.put(url, null);
            new DownloadImageTask(this).execute(url);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onDownloaded(String downloadUrl, Bitmap bitmap) {
        urlImageMap.put(downloadUrl, bitmap);

        notifyDataSetChanged();
    }

    private String getArtworkUrl(int i) {
        return getItem(i).getArtworkUrl100();
    }

}
