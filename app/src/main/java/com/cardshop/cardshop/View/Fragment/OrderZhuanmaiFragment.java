package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderZhuanmaiContract;
import com.cardshop.cardshop.R;

public class OrderZhuanmaiFragment extends BaseFragment implements OrderZhuanmaiContract.IView {
    private OrderZhuanmaiContract.IPresenter presenter;

    public static OrderZhuanmaiFragment newInstance() {
        return new OrderZhuanmaiFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(OrderZhuanmaiContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_zhuanmai, container, false);
    }
}
