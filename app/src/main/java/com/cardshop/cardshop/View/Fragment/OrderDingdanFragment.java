package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderDingdanContract;
import com.cardshop.cardshop.R;

public class OrderDingdanFragment extends BaseFragment implements OrderDingdanContract.IView {
    private OrderDingdanContract.IPresenter presenter;

    public static OrderDingdanFragment newInstance() {
        return new OrderDingdanFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return presenter;
    }


    @Override
    public void setPresenter(OrderDingdanContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_dingdan, container, false);
    }
}
