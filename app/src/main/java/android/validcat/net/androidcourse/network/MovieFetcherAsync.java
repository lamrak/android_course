package android.validcat.net.androidcourse.network;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieFetcherAsync extends AsyncTask<String, Integer, String> {
    private static final String LOG_TAG = MovieFetcherAsync.class.getSimpleName();
    private IResultListener listener;

    public MovieFetcherAsync(IResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                listener.onError("Read the input stream error");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");

            if (buffer.length() == 0) {
                listener.onError("Server response is empty");
                return null;
            }
            json = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            listener.onError(e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                    listener.onError(e.getMessage());
                }
            }
        }

        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null)
            listener.onResult(result);
    }

    public interface IResultListener {
        void onResult(@NonNull String result);
        void onError(String error);
    }
}
