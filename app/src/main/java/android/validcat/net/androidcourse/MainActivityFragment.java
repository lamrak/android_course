package android.validcat.net.androidcourse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        rv = (RecyclerView) root.findViewById(R.id.rv_movies);
        MovieAdapter mAdapter = new MovieAdapter();
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return root;
    }
}
