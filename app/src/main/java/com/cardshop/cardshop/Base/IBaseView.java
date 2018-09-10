package com.cardshop.cardshop.Base;

public interface IBaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

}
