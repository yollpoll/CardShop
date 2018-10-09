package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.CityModule;
import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends BaseFooterAdapter<List<CityModule>> {
    private List<CityModule> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public CityAdapter(List<CityModule> list, OnItemClickListener onItemClickListener) {
        super(list);
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citys, parent, false);
        return new CityAdapter.ViewHoler(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        super.onBindContentViewHolder(holder, position);
        ViewHoler viewHoler = (ViewHoler) holder;
        viewHoler.tvCity.setText(list.get(position).getName());
        viewHoler.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
    }

    public static class ViewHoler extends BaseViewHolder {
        TextView tvCity;
        LinearLayout llRoot;

        public ViewHoler(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tv_city);
            llRoot = itemView.findViewById(R.id.ll_root);
        }
    }
}
