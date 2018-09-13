package com.cardshop.cardshop.Base;

public interface IBaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void showSnackerToast(String content);

    void showToast(String content);

    void goBack();

    void showBack();
}
