package com.cardshop.cardshop.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.List;

public class BaseFragmentStatePagerAdapter extends BasePagerStateAdapter {
    private int currentPosition;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentPosition = position;
        super.setPrimaryItem(container, position, object);
    }

    public BaseFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm, list, title);
    }
}
