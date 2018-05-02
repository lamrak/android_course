package android.validcat.net.androidcourse.interfaces;

import android.validcat.net.androidcourse.model.Movie;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * Created by Oleksii on 12/5/15.
 */
public interface IMovieDAO<I> {
    long save(I i) throws IOException;
    boolean delete(I i);
    I get(int id);
    Observable<List<Movie>> getAll();

    void saveAll(List<Movie> movies);
}