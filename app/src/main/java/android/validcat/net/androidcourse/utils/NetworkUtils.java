package android.validcat.net.androidcourse.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager
                = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo()
                != null && connectivityManager.getActiveNetworkInfo()
                        .isConnected();
    }
}
