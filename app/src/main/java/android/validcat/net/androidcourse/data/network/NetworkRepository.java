package android.validcat.net.androidcourse.data.network;

import android.content.Context;
import android.validcat.net.androidcourse.data.db.Constants;

public class NetworkRepository {
    private final Context context;

    public NetworkRepository(Context context) {
        this.context = context;
    }

    public void getMoviesApi(MovieFetcherAsync.IResultListener listener) {
        new MovieFetcherAsync(listener).execute(Constants.URL_FETCH_MOVIES);
    }


}

