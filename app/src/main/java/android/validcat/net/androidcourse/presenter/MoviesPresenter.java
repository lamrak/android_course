package android.validcat.net.androidcourse.presenter;

import android.validcat.net.androidcourse.data.DataRepository;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.model.Movie;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesPresenter implements MVPMovies.MoviesPresenter {
    private MVPMovies.MoviesView view;

    private DataRepository model;

    public MoviesPresenter(DataRepository model) {
        this.model = model;
    }

    @Override
    public void setView(MVPMovies.MoviesView view) {
        this.view = view;
    }

    @Override
    public void getMovies() {
        //TODO show progress
        model.fetchMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {
                //TODO hide progress
            }

            @Override
            public void onError(Throwable e) {
                MoviesPresenter.this.onError((Exception) e);
            }

            @Override
            public void onNext(List<Movie> movies) {
                MoviesPresenter.this.updateUIWithResults(movies);
            }
        });
    }

    private void updateUIWithResults(List<Movie> movies) {
        if (MoviesPresenter.this.view != null)
            MoviesPresenter.this.view.onMoviesReceived(movies);
    }

    private void onError(Exception e) {
        //TODO
        // add new class that handles exception  like a
        // if (e instanceof IOException)
        // getSting(R.string.error_working_stream);
        if (MoviesPresenter.this.view != null)
            MoviesPresenter.this.view.onError(e.getLocalizedMessage());
    }


    @Override
    public void onMovieSelected(int id) {
        throw new UnsupportedOperationException();
    }
}
