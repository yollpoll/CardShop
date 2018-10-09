package com.cardshop.cardshop.PresenterImpl;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Contract.ChooseAreaContract;
import com.cardshop.cardshop.Module.CityModule;
import com.cardshop.cardshop.View.Fragment.AddAddressFragment;
import com.cardshop.cardshop.View.Fragment.CityFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChooseAreaPresenterImpl extends ChooseAreaContract.IPresenter {
    private ChooseAreaContract.IView mView;
    private Context context;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();
    private List<CityPresenterImpl> listImpl = new ArrayList<>();
    private String area = "";
    private int position = 0;

    public ChooseAreaPresenterImpl(ChooseAreaContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        init();
    }

    private void init() {
        CityFragment cityFragment = CityFragment.newInstance();
        listImpl.add(new CityPresenterImpl(cityFragment, onChooseListener, onFinishListener, CityModule.CHINA_CODE));
        titles.add("请选择");
        list.add(cityFragment);
        mView.initVP(list, titles);
    }

    private CityPresenterImpl.OnChooseListener onChooseListener = new CityPresenterImpl.OnChooseListener() {
        @Override
        public void onChoose(String code, String name, String pCode) {
            if (position != list.size() - 1) {
                //当前不是最后一页
                Iterator<String> iterator = titles.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (titles.indexOf(next) > position) {
                        iterator.remove();
                    }
                }
                Iterator<Fragment> iterator1 = list.iterator();
                while (iterator1.hasNext()) {
                    Fragment next = iterator1.next();
                    if (list.indexOf(next) > position) {
                        iterator1.remove();
                    }
                }
            }
            titles.set(titles.size() - 1, name);
            CityFragment cityFragment = CityFragment.newInstance();
            listImpl.add(new CityPresenterImpl(cityFragment, onChooseListener, onFinishListener, code));
            titles.add("请选择");
            list.add(cityFragment);
            mView.refresh();
        }
    };
    private CityPresenterImpl.OnFinishListener onFinishListener = new CityPresenterImpl.OnFinishListener() {
        @Override
        public void onFinsh() {
            area = "";
            for (int i = 0; i < titles.size() - 1; i++) {
                area += titles.get(i) + " ";
            }
            mView.onFinish(area.substring(0, area.lastIndexOf(" ")));
        }
    };

    @Override
    public void currentPager(int pager) {
        position = pager;
    }
}
