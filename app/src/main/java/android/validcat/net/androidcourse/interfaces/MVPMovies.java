package android.validcat.net.androidcourse.interfaces;


import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

public interface MVPMovies {

    interface MoviesView {
        void onMoviesReceived(List<Movie> movies);
        void onError(String error);
    }

    interface MoviesPresenter {
        void setView(MoviesView view);

        void getMovies();
        void onMovieSelected(int id);
    }

    interface MoviesModel {
        void fetchMovies(IListener resultListener);
    }
}
