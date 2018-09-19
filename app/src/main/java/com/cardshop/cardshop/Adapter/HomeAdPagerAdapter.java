package com.cardshop.cardshop.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeAdPagerAdapter extends PagerAdapter {
    private List<String> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public HomeAdPagerAdapter(List<String> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
        ImageView ivBanner = (ImageView) view.findViewById(R.id.iv_banner);
        ImageUtils.loadImage(list.get(position), ivBanner, container.getContext());
        container.addView(view);
        ivBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener)
                    onItemClickListener.onClick(v, position);
            }
        });
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 1;//建议值为0.6~1.0之间
    }
}
