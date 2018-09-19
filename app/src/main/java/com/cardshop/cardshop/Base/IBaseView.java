package com.cardshop.cardshop.Base;

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

}
