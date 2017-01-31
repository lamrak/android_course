package android.validcat.net.androidcourse.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.validcat.net.androidcourse.model.Movie;

public class MovieOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "movie_db";
    private static final int DB_VERSION = 1;

    public MovieOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Movie.TABLE_MOVIE + " (" +
                Movie.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Movie.KEY_TITLE + " TEXT NOT NULL," +
                Movie.KEY_OVERVIEW + " TEXT NOT NULL," +
                Movie.KEY_POSTER_PATH + " TEXT NOT NULL," +
                Movie.KEY_RATE + " INT NOT NULL" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + Movie.TABLE_MOVIE);
        onCreate(db);
    }
}
