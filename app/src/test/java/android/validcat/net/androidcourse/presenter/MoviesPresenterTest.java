package android.validcat.net.androidcourse.presenter;

import android.validcat.net.androidcourse.data.DataRepository;
import android.validcat.net.androidcourse.interfaces.MVPMovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MoviesPresenterTest {
    @Mock
    private MVPMovies.MoviesView view;
    @Mock
    private DataRepository model;

    MVPMovies.MoviesPresenter presenter;

    @Before
    public void setUp() {
        presenter = new MoviesPresenter(model);
        presenter.setView(view);
    }

    @Test()
    public void testFetchMoviesMainFlow() {
        presenter.getMovies();
        verify(view, times(1)).showProgressBar();
        verify(view, never()).hideProgressBar();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testMovieSelectedException() {
        presenter.onMovieSelected(0);
    }

}