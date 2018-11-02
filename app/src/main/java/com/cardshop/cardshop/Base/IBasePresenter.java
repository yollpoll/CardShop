package com.cardshop.cardshop.Base;

import android.content.Context;
import android.content.Intent;

public interface IBasePresenter {
    void start();

    void start(Context context);

    void stop();

    void onResume();

    void onReturnResult(int requestCode, int resultCode, Intent data);
}
