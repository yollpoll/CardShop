package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.OrderDingdanModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class OrderDingdanAdapter extends BaseFooterAdapter<List<OrderDingdanModule>> {
    private OnItemClickListener onItemClickListener;

    public OrderDingdanAdapter(List<OrderDingdanModule> orderDingdanModules, OnItemClickListener onItemClickListener) {
        super(orderDingdanModules);
        this.onItemClickListener = onItemClickListener;
        data = orderDingdanModules;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, int position) {
        super.onBindContentViewHolder(holder, position);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvDate, tvState, tvNum, tvCardName, tvNowPrice, tvOrgPrice, tvOrderId, tvAllPrice;
        ImageView ivCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvState = itemView.findViewById(R.id.tv_state);
            tvCardName = itemView.findViewById(R.id.tv_card_name);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvNowPrice = itemView.findViewById(R.id.tv_now_price);
            tvOrgPrice = itemView.findViewById(R.id.tv_org_price);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvAllPrice = itemView.findViewById(R.id.tv_all_price);
            ivCard = itemView.findViewById(R.id.iv_card);
        }
    }
}
