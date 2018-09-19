package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.FoundContract;
import com.cardshop.cardshop.Module.FoundGameModule;

import java.util.ArrayList;
import java.util.List;

public class FoundPresenterImpl extends FoundContract.IPresenter<FoundContract.IView> {

    private FoundContract.IView mView;

    private List<FoundGameModule.Entity> list = new ArrayList<>();

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

    }
}
