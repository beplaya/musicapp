package examples.gjames.com.musicapp._dal;

import android.os.AsyncTask;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringRequestTask extends AsyncTask<URL, Void, String> {

    private StringRequestTaskListener listener;
    private boolean error = false;

    public interface StringRequestTaskListener {
        void onRequestComplete(String string, boolean error);
    }

    public StringRequestTask(StringRequestTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        error = false;
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
            resultString = new String(ByteStreams.toByteArray(inputStream), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            error = true;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return resultString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onRequestComplete(s, error);
    }
}
