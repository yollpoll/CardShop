package com.cardshop.cardshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.FoundGameModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class FoundGamesAdapter extends FooterAdapter<List<FoundGameModule>, BaseViewHolder> {
    private List<FoundGameModule> list;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public FoundGamesAdapter(List<FoundGameModule> foundGameModules, OnItemClickListener onItemClickListener) {
        super(foundGameModules);
        this.list = foundGameModules;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.ivFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
    }

    @Override
    protected void onBindFooterViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_found, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    protected BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_adapter, parent, false);
        return new BaseViewHolder(view);
    }

    public static class ViewHolder extends BaseViewHolder {
        ImageView ivFound;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFound = itemView.findViewById(R.id.iv_game);
        }
    }
}
