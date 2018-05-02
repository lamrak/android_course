package android.validcat.net.androidcourse.data.network;

import android.validcat.net.androidcourse.BuildConfig;
import android.validcat.net.androidcourse.model.Movie;
import android.validcat.net.androidcourse.model.Page;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

public class RetrofitNetworkRepository {
    private RetrofitRestApi client;

    public RetrofitNetworkRepository() {
        String API_BASE_URL = "http://api.themoviedb.org/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        client = retrofit.create(RetrofitRestApi.class);
    }

    public Observable<List<Movie>> getMovies() {
        return client.getMovies("popularity.desc", BuildConfig.OPEN_MOVIE_DB_API_KEY)
                .map(new Func1<Page, List<Movie>>() {
                    @Override
                    public List<Movie> call(Page page) {
                        return page.getMovies();
                    }
                });
    }
}
