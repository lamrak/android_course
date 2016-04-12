package android.validcat.net.androidcourse.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * Created by Oleksii on 12/5/15.
 */
public interface IMovieDAO<I> {
    long save(I i) throws IOException;
    boolean delete(I i);
    I get(int id);
    List<I> getAll();
}