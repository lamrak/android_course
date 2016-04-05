package android.validcat.net.androidcourse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MovieAdapter extends RecyclerView.Adapter< MovieAdapter.ViewHolder> {

    // Provide a suitable constructor (depends on the kind of dataset)
    public  MovieAdapter() {
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
        holder.ivPoster.setImageResource(R.drawable.poster);
    }

    @Override
    public int getItemCount() {
        return 20;
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