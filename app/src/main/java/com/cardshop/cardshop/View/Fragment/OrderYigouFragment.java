package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderYigouContract;
import com.cardshop.cardshop.R;

public class OrderYigouFragment extends BaseFragment implements OrderYigouContract.IView {
    private OrderYigouContract.IPresenter presenter;

    public static OrderYigouFragment newInstance() {
        return new OrderYigouFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter = presenter;
    }

    @Override
    public void setPresenter(OrderYigouContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_yigou, container, false);
    }
}
