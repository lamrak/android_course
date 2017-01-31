package android.validcat.net.androidcourse.interfaces;

import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

public interface IListener {

    void onResult(List<Movie> movies);
    void onError(Exception e);
}
