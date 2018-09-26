package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.CardContract;
import com.cardshop.cardshop.R;

public class CardFragment extends BaseFragment implements CardContract.IView {
    private CardContract.IPresenter presenter;

    private RecyclerView rvCard;

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(CardContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvCard = view.findViewById(R.id.rv_card);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("银行卡");
        showBack();
        setNoStatusBar();
        setHeadRightIv(R.mipmap.icon_add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
