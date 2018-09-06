package com.cardshop.cardshop.Base;

public interface IBaseView<T extends IBasePresenter> {
    void setPresenter(T presenter);
}
