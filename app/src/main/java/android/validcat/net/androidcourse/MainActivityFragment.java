package android.validcat.net.androidcourse;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.validcat.net.androidcourse.db.Constants;
import android.validcat.net.androidcourse.model.Movie;
import android.validcat.net.androidcourse.network.MovieFetcherAsync;
import android.validcat.net.androidcourse.network.MovieNetworkParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private RecyclerView rv;
    private List<Movie> movies;
    private MovieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        movies = new ArrayList<>();
        rv = (RecyclerView) root.findViewById(R.id.rv_movies);
        adapter = new MovieAdapter(getContext(), movies);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),
                getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

        new MovieFetcherAsync(new MovieFetcherAsync.IResultListener() {
            @Override
            public void onResult(@NonNull String result) {
                try {
                    movies.addAll(MovieNetworkParser.getMoviesFromJson(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        }).execute(Constants.URL_FETCH_MOVIES);

        return root;
    }
}
