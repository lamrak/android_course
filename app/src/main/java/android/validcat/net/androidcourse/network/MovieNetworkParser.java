package android.validcat.net.androidcourse.network;

import android.validcat.net.androidcourse.db.Constants;
import android.validcat.net.androidcourse.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieNetworkParser {
    public static final String LOG_TAG = MovieNetworkParser.class.getSimpleName();
    private static final String KEY_RESULTS = "results";

    /**
     * Take the String representing the complete movies in JSON Format and get list of  MovieItem.
     *
     */
    public static List<Movie> getMoviesFromJson(String json) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        JSONObject forecastJson = new JSONObject(json);
        JSONArray jsonMoviesArray = forecastJson.getJSONArray(KEY_RESULTS);

        List<Movie> movies = new ArrayList<>(Constants.MOVIE_COUNT);
        // in case if receive movies less than want to show
        int length = forecastJson.length() < Constants.MOVIE_COUNT ? forecastJson.length() : Constants.MOVIE_COUNT;
        for(int i = 0; i < Constants.MOVIE_COUNT; i++) {
            Movie movie = new Movie();
            JSONObject jsonMovie = jsonMoviesArray.getJSONObject(i);
            movie.title = jsonMovie.getString(Movie.KEY_TITLE);
            movie.overview = jsonMovie.getString(Movie.KEY_OVERVIEW);
            movie.posterPath = jsonMovie.getString(Movie.KEY_POSTER_PATH);
            movie.release = jsonMovie.getString(Movie.KEY_RELEASE_DATE);
            movie.rate = jsonMovie.getString(Movie.KEY_RATE);

            movies.add(movie);
        }

        return movies;
    }
}
