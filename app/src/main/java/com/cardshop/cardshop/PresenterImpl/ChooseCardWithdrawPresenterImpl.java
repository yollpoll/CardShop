package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ChooseCardWidthdrawContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.Module.UserModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCardWithdrawPresenterImpl extends ChooseCardWidthdrawContract.IPresenter {
    private ChooseCardWidthdrawContract.IView mView;
    private List<CardModule> list = new ArrayList<>();

    public ChooseCardWithdrawPresenterImpl(ChooseCardWidthdrawContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        list.clear();
        mView.initRv(list);
    }

    @Override
    public void attach(Object view) {
        super.attach(view);
        getCard();

    }

    private void getCard() {
//        mView.showProgressbar();
        CardModule.getCardList(UserModule.getCurrentUser().getMember().getMemberId(), new Callback<ResponseData<List<CardModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<CardModule>>> call, Response<ResponseData<List<CardModule>>> response) {
                mView.hideProgressbar();
                if (response.body().isSuccess()) {
                    mView.hideError();
                    list.clear();
                    list.addAll(response.body().getData());
                    mView.onRefreshData();
                    if (list.size() == 0) {
                        mView.showNoData();
                    } else {
                        mView.hideNoData();
                    }
                } else {
                    mView.showError();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<CardModule>>> call, Throwable t) {
                mView.showError();
                mView.hideProgressbar();
            }
        });
    }

    @Override
    public void chooseCard(int position) {
        mView.onChooseCard(list.get(position));
    }

    @Override
    public void refresh() {
        getCard();
    }
}
