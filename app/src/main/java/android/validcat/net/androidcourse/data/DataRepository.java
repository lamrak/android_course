package android.validcat.net.androidcourse.data;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;
import android.validcat.net.androidcourse.data.db.DatabaseRepository;
import android.validcat.net.androidcourse.data.network.MovieFetcherAsync;
import android.validcat.net.androidcourse.data.network.MovieNetworkParser;
import android.validcat.net.androidcourse.data.network.NetworkRepository;
import android.validcat.net.androidcourse.interfaces.IListener;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.model.Movie;
import android.validcat.net.androidcourse.utils.NetworkUtils;

import org.json.JSONException;

import java.util.List;
import java.util.MissingResourceException;

public class DataRepository implements MVPMovies.MoviesModel {
    private final Context context;
    private final DatabaseRepository db;
    private final NetworkRepository rest;

    public DataRepository(Context context) {
        this.context = context;
        db = new DatabaseRepository(context);
        rest = new NetworkRepository(context);
    }

    @Override
    public void fetchMovies(final IListener presenterListener) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            List<Movie> dbMovies = db.getAll();
            if (dbMovies != null)
                presenterListener.onResult(dbMovies);
            else presenterListener.onError(new MissingResourceException("", "", ""));
        } else {
            rest.getMoviesApi(new MovieFetcherAsync.IResultListener() {
                @Override
                public void onResult(@NonNull String result) {
                    try {
                        List<Movie> fetchedMovies = MovieNetworkParser.getMoviesFromJson(result);
                        db.saveAll(fetchedMovies);
                        presenterListener.onResult(fetchedMovies);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        presenterListener.onError(e);
                    }
                }

                @Override
                public void onError(String error) {
                    presenterListener.onError(new NetworkErrorException(error));
                }
            });
        }

    }

}
