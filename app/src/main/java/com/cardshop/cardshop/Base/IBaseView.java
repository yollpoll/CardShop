package com.cardshop.cardshop.Base;

import android.content.Intent;

public interface IBaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void showSnackerToast(String content);

    void showToast(String content);

    void goBack();

    void setTitle(String title);

    void showBack();

    void showLoading(String title, String message);

    void hideLoading();

    void setNoStatusBar();

    void showProgressbar();

    void hideProgressbar();

    void onReturnResult(int requestCode, int resultCode, Intent data);

    void showNoData();


}
