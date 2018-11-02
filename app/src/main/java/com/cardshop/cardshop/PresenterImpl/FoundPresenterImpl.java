package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Contract.FoundContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.FoundGameModule;
import com.cardshop.cardshop.View.Activity.WebActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoundPresenterImpl extends FoundContract.IPresenter<FoundContract.IView> {

    private FoundContract.IView mView;

    private List<FoundGameModule> list = new ArrayList<>();

    public FoundPresenterImpl(FoundContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initGames(list);
        mView.setTitle("发现");
        refreshData();
    }

    @Override
    public void refreshData() {
        FoundGameModule.getFoundList(new Callback<ResponseData<List<FoundGameModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<FoundGameModule>>> call, Response<ResponseData<List<FoundGameModule>>> response) {
                mView.hideProgressbar();
                if (response.body().isSuccess() && response.isSuccessful()) {
                    list.clear();
                    list.addAll(response.body().getData());
                    if (list.size() == 0) {
                        mView.showNoData();
                    } else {
                        mView.refresh();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<FoundGameModule>>> call, Throwable t) {
                mView.hideProgressbar();
                mView.showError();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if (TextUtils.isEmpty(list.get(position).getUrl())) {
            return;
        }
        WebActivity.gotoWebActivity(list.get(position).getUrl(), mView.getmContext());
    }
}
