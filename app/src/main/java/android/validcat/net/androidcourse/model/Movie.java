package android.validcat.net.androidcourse.model;

import android.database.Cursor;

/**
 * Created by Oleksii on 4/11/16.
 */
public class Movie {
    // keys for packing/unpacking intent
    // for url building
    public static final String WIDTH_154 = "w154";
    public static final String WIDTH_342 = "w342";
    public static final String WIDTH_500 = "w500";
    public static final String WIDTH_780 = "w780";

    private static final String URL_IMAGE_TMDB_DEFAULT = "http://image.tmdb.org/t/p/";
    public static final String KEY_TITLE = "original_title";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_RATE = "vote_average";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_ID = "id";
    public static final String TABLE_MOVIE = "movies";

    public static String[] projection = {
            KEY_ID,
            KEY_TITLE,
            KEY_OVERVIEW,
            KEY_POSTER_PATH,
            KEY_RATE
    };

    public int id;
    public String title;
    public String posterPath;
    public String overview;
    public String release;
    public String rate;
    public boolean favorite;
//    private List<String> trailers;
//    private List<String> post;

    public String getFullPosterPath(String preferedWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL_IMAGE_TMDB_DEFAULT);
        sb.append(preferedWidth);
        sb.append(posterPath);

        return sb.toString();
    }

    public static Movie getItemFromCursor(Cursor c) {
        Movie item = new Movie();
        item.id = c.getInt(c.getColumnIndex(Movie.KEY_ID));
        item.title = c.getString(c.getColumnIndex(Movie.KEY_TITLE));
        item.overview = c.getString(c.getColumnIndex(Movie.KEY_OVERVIEW));
        item.posterPath = c.getString(c.getColumnIndex(Movie.KEY_POSTER_PATH));
        item.rate = c.getString(c.getColumnIndex(Movie.KEY_RATE));

        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movieItem = (Movie) o;

        if (id != movieItem.id) return false;
        return title.equals(movieItem.title);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[Movie Item {title="+ title +", id= "+ id +"}]";
    }
}
