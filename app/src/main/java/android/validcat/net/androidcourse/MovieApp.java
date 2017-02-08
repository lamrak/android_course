package android.validcat.net.androidcourse;

import android.app.Application;
import android.validcat.net.androidcourse.dependency.component.ApplicationComponent;
import android.validcat.net.androidcourse.dependency.component.DaggerApplicationComponent;
import android.validcat.net.androidcourse.dependency.modules.ApplicationModule;

public class MovieApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
