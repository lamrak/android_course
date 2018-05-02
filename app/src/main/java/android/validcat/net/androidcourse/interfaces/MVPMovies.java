package android.validcat.net.androidcourse.interfaces;


import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

import rx.Observable;

public interface MVPMovies {

    interface MoviesView {
        void onMoviesReceived(List<Movie> movies);
        void onError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface MoviesPresenter {
        void setView(MoviesView view);

        void getMovies();
        void onMovieSelected(int id);
    }

    interface MoviesModel {
        Observable<List<Movie>> fetchMovies();
    }
}
