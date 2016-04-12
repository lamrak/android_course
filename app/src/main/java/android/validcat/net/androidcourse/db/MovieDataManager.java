package android.validcat.net.androidcourse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.validcat.net.androidcourse.interfaces.IMovieDAO;
import android.validcat.net.androidcourse.model.Movie;

import java.io.IOException;
import java.util.List;

public class MovieDataManager implements IMovieDAO<Movie> {
    private MovieOpenHelper dbHelper;

    public MovieDataManager(Context context) {
        dbHelper = new MovieOpenHelper(context);
    }

    @Override
    public long save(Movie movie) throws IOException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Movie.KEY_TITLE, movie.title);
        cv.put(Movie.KEY_OVERVIEW, movie.overview);
        cv.put(Movie.KEY_RATE, movie.rate);
        cv.put(Movie.KEY_POSTER_PATH, movie.posterPath);
        cv.put(Movie.KEY_ID, movie.id);

        long _id = db.insert(Movie.TABLE_MOVIE, null, cv);
        db.close();

        return _id;
    }

    @Override
    public boolean delete(Movie Movie) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] whereArgs = {String.valueOf(Movie.id)};
        int rows = db.delete(Movie.TABLE_MOVIE, Movie.KEY_ID,
                whereArgs);
        db.close();

        return rows > 0;
    }

    @Override
    public Movie get(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        Cursor c = db.query(Movie.TABLE_MOVIE,
                Movie.projection,
                Movie.KEY_ID + " = ? ",
                whereArgs,
                null,
                null,
                null);

        Movie item = null;
        while (c != null && c.moveToFirst()) {
            item = Movie.getItemFromCursor(c);
        }

        return item;
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }
}
