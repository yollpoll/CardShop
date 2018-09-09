package com.cardshop.framework.Base;

import android.app.Application;

public class SpqApplication extends Application {
    private static SpqApplication instance;

    public static SpqApplication getInstance() {
        if (instance == null) {
            instance = new SpqApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
