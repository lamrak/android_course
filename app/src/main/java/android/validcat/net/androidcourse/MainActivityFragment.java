package android.validcat.net.androidcourse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.validcat.net.androidcourse.network.MovieFetcherAsync;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        rv = (RecyclerView) root.findViewById(R.id.rv_movies);
        MovieAdapter mAdapter = new MovieAdapter();
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        MovieFetcherAsync task = new MovieFetcherAsync(new MovieFetcherAsync.IResultListener() {
            @Override
            public void onResult(String result) {
                // result ok
                Log.d(LOG_TAG, "Server Result=" + result);
            }

            @Override
            public void onError(String error) {
                // error
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        //BuildConfig.OPEN_WEATHER_MAP_API_KEY
        task.execute("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + BuildConfig.OPEN_MOVIE_DB_API_KEY);

        return root;
    }
}
