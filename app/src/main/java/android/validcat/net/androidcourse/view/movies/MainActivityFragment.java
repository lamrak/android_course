package android.validcat.net.androidcourse.view.movies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.validcat.net.androidcourse.MovieApp;
import android.validcat.net.androidcourse.R;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.model.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivityFragment extends Fragment
        implements MVPMovies.MoviesView {
    private List<Movie> movies;
    private MovieAdapter adapter;

    @Inject
    public MVPMovies.MoviesPresenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    private void initialize() {
        ((MovieApp) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        initUI(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.setView(this);
        presenter.getMovies();
    }

    private void initUI(View root) {
        movies = new ArrayList<>();
        RecyclerView rv = (RecyclerView) root.findViewById(R.id.rv_movies);
        adapter = new MovieAdapter(getContext(), movies);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),
                getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

    @Override
    public void onMoviesReceived(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), error,
                Toast.LENGTH_SHORT).show();
    }
}
