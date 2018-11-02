package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.List;

public class OrderYigouAdapter extends BaseFooterAdapter<List<OrderYigouModule>> {
    private OnItemClickListener onItemClickListener;

    public OrderYigouAdapter(List<OrderYigouModule> list, OnItemClickListener onItemClickListener) {
        super(list);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderyigou, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        super.onBindContentViewHolder(holder, position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvCard.setText(data.get(position).getGoodsName());
        viewHolder.tvCount.setText("x" + data.get(position).getGoodsNum());
        ImageUtils.loadImage(data.get(position).getGoodsImage(), viewHolder.ivCard, mContext);
        viewHolder.btnGetGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvCard, tvCount;
        ImageView ivCard;
        TextView btnGetGoods;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCard = itemView.findViewById(R.id.tv_card_name);
            ivCard = itemView.findViewById(R.id.iv_card);
            tvCount = itemView.findViewById(R.id.tv_count);
            btnGetGoods = itemView.findViewById(R.id.btn_get_goods);
        }
    }
}
