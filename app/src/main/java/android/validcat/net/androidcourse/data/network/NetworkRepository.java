package android.validcat.net.androidcourse.data.network;

import android.content.Context;
import android.util.Log;
import android.validcat.net.androidcourse.data.db.Constants;
import android.validcat.net.androidcourse.model.Movie;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static android.validcat.net.androidcourse.data.network.MovieNetworkParser.LOG_TAG;

public class NetworkRepository {
    private final Context context;

    public NetworkRepository(Context context) {
        this.context = context;
    }

    public Observable<List<Movie>> getMovies() {
        return Observable.create(new Observable.OnSubscribe<List<Movie>>() {
            @Override
            public void call(Subscriber<? super List<Movie>> subscriber) {
                //TODO check for internet connection
                try {
                    subscriber.onNext(getTopMoviesApi());
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private List<Movie> getTopMoviesApi() throws JSONException, IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL((Constants.URL_FETCH_MOVIES));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null)
                throw new IOException();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");

            if (buffer.length() == 0) {
                throw new IOException();
            }
            json = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            throw new IOException();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return MovieNetworkParser.getMoviesFromJson(json);
    }

}

