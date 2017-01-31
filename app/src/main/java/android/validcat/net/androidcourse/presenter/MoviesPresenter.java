package android.validcat.net.androidcourse.presenter;

import android.content.Context;
import android.validcat.net.androidcourse.interfaces.IListener;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.data.DataRepository;
import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

public class MoviesPresenter implements MVPMovies.MoviesPresenter {
    private MVPMovies.MoviesView view;
    private final MVPMovies.MoviesModel model;

    public MoviesPresenter(Context context) {
        model = new DataRepository(context);
    }

    @Override
    public void setView(MVPMovies.MoviesView view) {
        this.view = view;
    }

    @Override
    public void getMovies() {
        model.fetchMovies(new IListener() {
            @Override
            public void onResult(List<Movie> movies) {
                if (MoviesPresenter.this.view != null)
                    MoviesPresenter.this.view.onMoviesReceived(movies);
            }

            @Override
            public void onError(Exception e) {
                if (MoviesPresenter.this.view != null)
                    MoviesPresenter.this.view.onError(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onMovieSelected(int id) {
        //TODO
    }
}
