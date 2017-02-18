package android.validcat.net.androidcourse.utils;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Oleksii on 2/14/17.
 */

public class RxHelperUtils {


    public void setup() {

        Observable<Integer> emitter = Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(1);
                        subscriber.onNext(2);
                        subscriber.onCompleted();
                    }
                });

        emitter.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("Rx", "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Rx", "onError()");
            }

            @Override
            public void onNext(Integer value) {
                Log.i("Rx", "onNext()" + value);
            }
        });

    }
}
