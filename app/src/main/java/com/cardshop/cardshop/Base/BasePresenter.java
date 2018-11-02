package com.cardshop.cardshop.Base;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> implements IBasePresenter,Serializable {
    protected Context mContext;

    @Override
    public void start() {

    }

    @Override
    public void start(Context context) {
        this.mContext = context;
    }

    @Override
    public void stop() {

    }

    private WeakReference<V> weakRefView;

    public void attach(V view) {
        weakRefView = new WeakReference<V>(view);
    }

    public void detach() {
        if (isAttach()) {
            weakRefView.clear();
            weakRefView = null;
        }
    }

    public V obtainView() {
        return isAttach() ? weakRefView.get() : null;
    }

    protected boolean isAttach() {
        return weakRefView != null &&
                weakRefView.get() != null;
    }

    @Override
    public void onReturnResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onResume() {

    }
}
