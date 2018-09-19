package com.cardshop.cardshop.Utils;

import com.cardshop.cardshop.Listener.OnRxScrollListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    public static boolean isScrollAnnouncement = true;
    public static boolean isScrollBanner = true;

    public static void StartScroll(final List<String> list, final OnRxScrollListener onRxScrollListener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                while (isScrollAnnouncement) {
                    for (int i = 0; i < list.size(); i++) {
                        Thread.sleep(5000);
                        emitter.onNext(i);
                    }
                }
                emitter.onComplete();
            }

        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer s) {
                        onRxScrollListener.onScroll(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public static void StartScrollBanner(final List<String> list, final OnRxScrollListener onRxScrollListener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                while (isScrollBanner) {
                    for (int i = 0; i < list.size(); i++) {
                        Thread.sleep(5000);
                        emitter.onNext(i);
                    }
                }
                emitter.onComplete();
            }

        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer s) {
                        onRxScrollListener.onScroll(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    public static void stopScrollAnnouncement() {
        isScrollAnnouncement = false;
    }

    public static void stopScrollBanner() {
        isScrollBanner = false;
    }

}
