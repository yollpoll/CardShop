package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Module.OrderZhuanmaiModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DateUtils;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.List;

public class OrderZhuanmaiAdapter extends BaseFooterAdapter<List<OrderZhuanmaiModule>> {

    public OrderZhuanmaiAdapter(List<OrderZhuanmaiModule> orderZhuanmaiModules) {
        super(orderZhuanmaiModules);
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_zhuanmai, parent, false);
        return new ZhuanmaiViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, int position) {
        super.onBindContentViewHolder(holder, position);
        OrderZhuanmaiModule module = data.get(position);
        ZhuanmaiViewHolder viewHolder = (ZhuanmaiViewHolder) holder;
        viewHolder.tvCode.setText("编号:" + module.getCode());
        viewHolder.tvDate.setText(DateUtils.getDate(Long.parseLong(module.getAddTime())  * 1000));
        viewHolder.tvCardName.setText(module.getGoodsName());
        viewHolder.tvPrice.setText("转卖价: " + module.getAllPrice() + "/张");
        viewHolder.tvCount.setText("转卖数量: " + module.getGoodsNum() + "张");
        ImageUtils.loadImage(module.getGoodsImage(), ((ZhuanmaiViewHolder) holder).ivCard, mContext);
    }

    public static class ZhuanmaiViewHolder extends BaseViewHolder {
        TextView tvCode, tvDate, tvCardName, tvPrice, tvCount;
        ImageView ivCard;

        public ZhuanmaiViewHolder(View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCardName = itemView.findViewById(R.id.tv_card_name);
            tvPrice = itemView.findViewById(R.id.tv_zhuanmai_price);
            tvCount = itemView.findViewById(R.id.tv_count);
            ivCard = itemView.findViewById(R.id.iv_card);
        }
    }
}
