package android.validcat.net.androidcourse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.validcat.net.androidcourse.model.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter< MovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public  MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public  MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie m = movies.get(position);
        //String url = Movie.getFullPosterPath(m.posterPath, Movie.WIDTH_342); // cursor.getString(MovieItem.COL_MOVIE_THUMB_PATH);
        if (!TextUtils.isEmpty(m.getFullPosterPath(Movie.WIDTH_500)))
            Picasso.with(context)
                    .load(m.getFullPosterPath(Movie.WIDTH_500))
                    .placeholder(R.drawable.image_placeholder)
                    .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPoster;

        public ViewHolder(View v) {
            super(v);
            ivPoster = (ImageView) v.findViewById(R.id.poster);
        }
    }

}