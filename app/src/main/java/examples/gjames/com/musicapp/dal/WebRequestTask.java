package examples.gjames.com.musicapp.dal;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebRequestTask extends AsyncTask<URL, Integer, String> {
    @Override
    protected String doInBackground(URL... urls) {
        if (urls.length == 0) {
            throw new IllegalArgumentException("Must specify URL");
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) urls[0].openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            new String(ByteStreams.toByteArray(inputStream),Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
