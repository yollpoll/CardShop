package com.cardshop.cardshop.Adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.OrderDingdanModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DateUtils;
import com.cardshop.framework.Utils.ImageUtils;

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
        OrderDingdanModule dingdanModule = data.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvOrgPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.tvOrgPrice.setText(dingdanModule.getGoodsPrice());
        viewHolder.tvCardName.setText(dingdanModule.getGoodsName());
        viewHolder.tvDate.setText(DateUtils.getDate(Long.parseLong(dingdanModule.getAddTime())*1000));
        viewHolder.tvState.setText(OrderDingdanModule.getStatusName(dingdanModule.getCardStatus()));
        viewHolder.tvNowPrice.setText(dingdanModule.getAllPrice());
        viewHolder.tvNum.setText("x" + dingdanModule.getGoodsNum());
        viewHolder.tvOrderId.setText(dingdanModule.getOrderSn());
        viewHolder.tvAllPrice.setText("合计: ¥" + dingdanModule.getTotalPrice());
        ImageUtils.loadImage(dingdanModule.getGoodsImage(), viewHolder.ivCard, mContext);
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
