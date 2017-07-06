package examples.gjames.com.musicapp.views.lyrics;


import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import examples.gjames.com.musicapp._dal.DownloadImageTask;
import examples.gjames.com.musicapp._dal.apis.itunes.models.ItunesSearchResult;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.controllers.lyrics.LyricsController;
import examples.gjames.com.musicapp.views._MusicAppView;

import static examples.gjames.com.musicapp.R.id;
import static examples.gjames.com.musicapp.R.layout;

public class LyricsView extends _MusicAppView<LyricsController> {

    private ImageView ivAlbumArt;
    private TextView tvAlbumName;
    private TextView tvArtistName;
    private TextView tvTrackName;

    @Override
    protected void bind(_MusicAppActivity activity, _MusicAppController controller) {
        tvAlbumName = findTextView(id.result_item_tv_album_name);
        tvArtistName = findTextView(id.result_item_tv_artist_name);
        tvTrackName = findTextView(id.result_item_tv_track_name);
        ivAlbumArt = findImageView(id.result_item_iv_album_image);
    }

    @Override
    protected int getLayoutId() {
        return layout.activity_lyrics;
    }

    public void update(ItunesSearchResult item) {
        tvAlbumName.setText(item.getCollectionName());
        tvArtistName.setText(item.getArtistName());
        tvTrackName.setText(item.getTrackName());
        new DownloadImageTask(new DownloadImageTask.DownloadImageListener() {
            @Override
            public void onDownloaded(String url, Bitmap bitmap) {
                if (bitmap != null) {
                    findImageView(id.result_item_iv_album_image).setImageBitmap(bitmap);
                }
            }
        }).execute(item.getArtworkUrl100());
    }

}
