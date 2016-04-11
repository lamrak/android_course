package android.validcat.net.androidcourse.model;

/**
 * Created by Oleksii on 4/11/16.
 */
public class Movie {
    // keys for packing/unpacking intent
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_THUMB_PATH = "key_path";
    private static final String KEY_OVERVIEW = "key_overview";
    private static final String KEY_RELEASE = "key_release";
    private static final String KEY_RATE = "key_rate";
    // for url building
    public static final String WIDTH_154 = "w154";
    public static final String WIDTH_342 = "w342";
    public static final String WIDTH_500 = "w500";
    public static final String WIDTH_780 = "w780";

    private static final String URL_IMAGE_TMDB_DEFAULT = "http://image.tmdb.org/t/p/";
    // We can use here getter and setter but to simplify working with item I decided to
    // use direct field call (item.title).
    public long id;
    public String title;
    public String thumbPath;
    public String overview;
    public String release;
    public String rate;
    public boolean favorite;
    public long movieId;
//    private List<String> trailers;
//    private List<String> post;

    public String getFullPosterPath(String preferedWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL_IMAGE_TMDB_DEFAULT);
        sb.append(preferedWidth);
        sb.append(thumbPath);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[Movie Item {title="+ title +", id= "+ id +"}]";
    }
}
