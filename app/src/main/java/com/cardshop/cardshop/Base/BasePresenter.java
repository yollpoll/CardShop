package com.cardshop.cardshop.Base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> implements IBasePresenter {
    @Override
    public void start() {

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
}
