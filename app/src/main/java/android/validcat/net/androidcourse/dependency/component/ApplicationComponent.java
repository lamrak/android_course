package android.validcat.net.androidcourse.dependency.component;

import android.validcat.net.androidcourse.dependency.modules.ApplicationModule;
import android.validcat.net.androidcourse.dependency.modules.DataModule;
import android.validcat.net.androidcourse.view.movies.MainActivityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainActivityFragment fragment);
}
