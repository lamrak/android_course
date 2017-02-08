package android.validcat.net.androidcourse.data.db;

import android.validcat.net.androidcourse.BuildConfig;

/**
 * Created by Oleksii on 4/11/16.
 */
public class Constants {
    public static final int MOVIE_COUNT = 20;
    public static final String URL_FETCH_MOVIES =
            "http://test.api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + BuildConfig.OPEN_MOVIE_DB_API_KEY;
}
