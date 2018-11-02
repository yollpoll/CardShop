package com.cardshop.cardshop.PresenterImpl;

import android.app.Activity;
import android.content.Context;

import com.cardshop.cardshop.Contract.SettingContract;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.framework.glide.GlideCacheUtil;

public class SettingPresenterImpl extends SettingContract.IPresenter<SettingContract.IView> {
    private SettingContract.IView mView;
    private Context context;

    public SettingPresenterImpl(SettingContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        mView.setCache(GlideCacheUtil.getInstance().getCacheSize(context));
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.openGesture(SPUtiles.isOpenGesture());
    }

    @Override
    public void openGesture(boolean open) {
        SPUtiles.saveIsOpenGesture(open);
    }

    @Override
    public void clearCache(final Context context) {
        mView.showLoading("清除缓存", "清除缓存中");
        GlideCacheUtil.getInstance().clearImageDiskCache(context, new GlideCacheUtil.ClearListener() {
            @Override
            public void onClear() {
                updateCache();
            }
        });
    }

    @Override
    public void logout() {
        UserModule.logout();
    }

    private void updateCache() {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //此时已在主线程中，可以更新UI了
                mView.hideLoading();
                mView.showSnackerToast("清除完成");
                mView.setCache(GlideCacheUtil.getInstance().getCacheSize(context));
            }
        });
    }
}
