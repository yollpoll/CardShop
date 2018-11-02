package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.ViewHolder.NoMoreHolder;
import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnNoMoreCliclListener;
import com.cardshop.cardshop.R;

import java.util.List;

public class BaseFooterAdapter<D extends List> extends FooterAdapter<D, BaseViewHolder> {
    protected OnNoMoreCliclListener onNoMoreCliclListener;

    public void setOnNoMoreCliclListener(OnNoMoreCliclListener onNoMoreCliclListener) {
        this.onNoMoreCliclListener = onNoMoreCliclListener;
    }

    public BaseFooterAdapter(D d) {
        super(d);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    protected void onBindFooterViewHolder(BaseViewHolder holder, int position) {
        NoMoreHolder holder1 = (NoMoreHolder) holder;
        switch (getStatus()) {
            case FOOTER_TYPE_NOMORE:
                holder1.tvNoMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onNoMoreCliclListener)
                            onNoMoreCliclListener.onClick();
                    }
                });
                holder1.tvNoMore.setVisibility(View.VISIBLE);
                holder1.progressBar.setVisibility(View.GONE);
                break;
            case FOOTER_TYPE_LOADING:
                holder1.progressBar.setVisibility(View.VISIBLE);
                holder1.tvNoMore.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_adapter, parent, false);
        return new NoMoreHolder(view);
    }
}
