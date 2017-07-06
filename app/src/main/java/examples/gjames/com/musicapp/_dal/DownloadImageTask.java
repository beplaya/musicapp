package examples.gjames.com.musicapp._dal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private String url;
    private DownloadImageListener listener;

    public interface DownloadImageListener {
        void onDownloaded(String url, Bitmap bitmap);
    }

    public DownloadImageTask(DownloadImageListener listener) {
        this.listener = listener;
    }

    protected Bitmap doInBackground(String... urls) {
        url = urls[0];
        Bitmap bitmap;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        listener.onDownloaded(url, bitmap);
    }
}