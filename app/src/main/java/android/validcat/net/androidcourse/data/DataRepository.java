package android.validcat.net.androidcourse.data;

import android.content.Context;
import android.validcat.net.androidcourse.data.db.DatabaseRepository;
import android.validcat.net.androidcourse.data.network.RetrofitNetworkRepository;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class DataRepository implements MVPMovies.MoviesModel {
    private final DatabaseRepository db;
    private final RetrofitNetworkRepository rest;

    public DataRepository(Context context) {
        db = new DatabaseRepository(context);
        rest = new RetrofitNetworkRepository();
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return db.getAll().switchIfEmpty(
                rest.getMovies()
                .doOnNext(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> movies) {
                        if (movies != null)
                            db.saveAll(movies);
                    }
                }));

    }

}
