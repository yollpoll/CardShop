package com.cardshop.cardshop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BasePagerAdapter;

import java.util.List;

public class FragmentPagerAdapter extends BasePagerAdapter {
    private int currentPosition;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentPosition = position;
        super.setPrimaryItem(container, position, object);
    }

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm, list, title);
    }

}
