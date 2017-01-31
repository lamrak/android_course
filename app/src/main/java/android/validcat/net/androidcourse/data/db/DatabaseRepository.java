package android.validcat.net.androidcourse.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.validcat.net.androidcourse.interfaces.IMovieDAO;
import android.validcat.net.androidcourse.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements IMovieDAO<Movie> {
    private static final String LOG_TAG = DatabaseRepository.class.getSimpleName();
    private MovieOpenHelper dbHelper;

    public DatabaseRepository(Context context) {
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(Movie.TABLE_MOVIE,
                Movie.projection,
                null,
                null,
                null,
                null,
                null);

        if (c == null)
            return null;

        List<Movie> items = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Movie item = Movie.getItemFromCursor(c);
                items.add(item);
            } while (c.moveToNext());
        }

        c.close();

        return items;
    }

    @Override
    public void saveAll(List<Movie> movies) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (Movie movie: movies) {
            ContentValues cv = new ContentValues();
            cv.put(Movie.KEY_TITLE, movie.title);
            cv.put(Movie.KEY_OVERVIEW, movie.overview);
            cv.put(Movie.KEY_RATE, movie.rate);
            cv.put(Movie.KEY_POSTER_PATH, movie.posterPath);
            long id = db.insert(Movie.TABLE_MOVIE, null, cv);
            Log.d(LOG_TAG, "Inserted id=" + id);
        }

        db.close();
    }
}
