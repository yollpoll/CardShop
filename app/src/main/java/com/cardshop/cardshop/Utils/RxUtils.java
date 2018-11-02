package com.cardshop.cardshop.Utils;

import android.util.Log;

import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Listener.OnRxScrollListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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

    /**
     * 计时器
     * 单位毫秒
     *
     * @param time  总时间
     * @param space 时间间隔
     */
    public static void startCountDown(int time, final int space, final CountDownListener listener) {
        isStopCountDown = false;
        final int count = time / space;
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                int allCount = count;
                while (allCount >= 0 && !isStopCountDown) {
                    emitter.onNext(allCount);
                    allCount--;
                    try {
                        Thread.sleep(space);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("spq", "停止倒计时");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        listener.onCountDown(integer);
                    }
                });
    }

    public static boolean isStopCountDown = false;
}
