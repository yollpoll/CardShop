package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Contract.CityContract;
import com.cardshop.cardshop.Module.CityModule;

import java.util.ArrayList;
import java.util.List;

public class CityPresenterImpl extends CityContract.IPresenter {
    private CityContract.IView mView;
    private String provinceCode;
    private Context context;
    private OnChooseListener onChooseListener;
    private OnFinishListener onFinishListener;
    private List<CityModule> list = new ArrayList<>();

    public CityPresenterImpl(CityContract.IView mView, OnChooseListener onChooseListener, OnFinishListener onFinishListener,
                             String provinceCode) {
        this.mView = mView;
        mView.setPresenter(this);
        this.onFinishListener = onFinishListener;
        this.onChooseListener = onChooseListener;
        this.provinceCode = provinceCode;
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        list.clear();
        list.addAll(CityModule.getCity(context, provinceCode));
        if (list.size() == 0) {
            onFinishListener.onFinsh();
        } else {
            mView.initRv(list);
        }
    }

    @Override
    public void checkEd(int position) {
        onChooseListener.onChoose(list.get(position).getCode(), list.get(position).getName(), list.get(position).getpCode());
    }

    public interface OnChooseListener {
        void onChoose(String code, String name, String pCode);
    }

    public interface OnFinishListener {
        void onFinsh();
    }

}
