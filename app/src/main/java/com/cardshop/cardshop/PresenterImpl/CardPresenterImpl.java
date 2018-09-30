package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.CardContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.Module.UserModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardPresenterImpl extends CardContract.IPresenter<CardContract.IView> {
    private CardContract.IView mView;
    private List<CardModule> list = new ArrayList<>();

    public CardPresenterImpl(CardContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initRvCard(list);
        getCadrd();
    }

    private void getCadrd() {
        CardModule.getCardList(UserModule.getCurrentUser().getMember().getMemberId() + "", new Callback<ResponseData<List<CardModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<CardModule>>> call, Response<ResponseData<List<CardModule>>> response) {
                list.clear();
                list.addAll(response.body().getData());
                mView.refreshRv();
            }

            @Override
            public void onFailure(Call<ResponseData<List<CardModule>>> call, Throwable t) {

            }
        });
    }
}
