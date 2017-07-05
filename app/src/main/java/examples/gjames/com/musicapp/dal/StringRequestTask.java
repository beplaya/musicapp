package examples.gjames.com.musicapp.dal;

import android.os.AsyncTask;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringRequestTask extends AsyncTask<URL, Integer, String> {

    private StringRequestTaskListener listener;

    public interface StringRequestTaskListener {
        void onComplete(String string);
    }

    public StringRequestTask(StringRequestTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(URL... urls) {
        String resultString = null;
        if (urls.length == 0) {
            throw new IllegalArgumentException("Must specify URL");
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) urls[0].openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            new String(ByteStreams.toByteArray(inputStream), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return resultString;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onComplete(s);
    }
}
