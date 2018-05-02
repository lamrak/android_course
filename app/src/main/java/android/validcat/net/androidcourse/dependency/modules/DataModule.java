package android.validcat.net.androidcourse.dependency.modules;

import android.content.Context;
import android.validcat.net.androidcourse.data.DataRepository;
import android.validcat.net.androidcourse.interfaces.MVPMovies;
import android.validcat.net.androidcourse.presenter.MoviesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataModule {

    @Provides
    @Singleton
    DataRepository provideDataRepository(Context context) {
        return new DataRepository(context);
    }

    @Provides
    @Singleton
    MVPMovies.MoviesPresenter provideMoviePresenter(DataRepository repository) {
        return new MoviesPresenter(repository);
    }
}
