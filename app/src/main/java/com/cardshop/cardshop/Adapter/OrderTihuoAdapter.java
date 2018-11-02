package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Module.OrderTihuoModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DateUtils;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.List;

public class OrderTihuoAdapter extends BaseFooterAdapter<List<OrderTihuoModule>> {

    private boolean isShopingCard = false;

    public OrderTihuoAdapter(List<OrderTihuoModule> orderTihuoModules, boolean isShopingCard) {
        super(orderTihuoModules);
        this.isShopingCard = isShopingCard;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_tihuo, parent, false);
        return new TihuoHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, int position) {
        super.onBindContentViewHolder(holder, position);
        TihuoHolder tihuoHolder = (TihuoHolder) holder;
        OrderTihuoModule orderTihuoModule = data.get(position);
        tihuoHolder.tvCode.setText("编号:" + orderTihuoModule.getTihuoCode());
        tihuoHolder.tvDate.setText(DateUtils.getDate(Long.parseLong(orderTihuoModule.getAddTime()) * 1000));
        ImageUtils.loadImage(orderTihuoModule.getGoodsImage(), tihuoHolder.ivCard, mContext);
        tihuoHolder.tvCardName.setText(orderTihuoModule.getGoodsName());
        tihuoHolder.tvCount.setText("数量:" + orderTihuoModule.getGoodsNum());
        tihuoHolder.tvStatus.setText(getStatus(orderTihuoModule));
        if (!isShopingCard) {
            tihuoHolder.tvMsg.setText("手机号:" + orderTihuoModule.getPhone());
        } else {
            tihuoHolder.tvMsg.setText("快递单号:" + orderTihuoModule.getExpressCode());
        }
        tihuoHolder.tvAllPrice.setText("合计" + orderTihuoModule.getGoodsPrice());
    }

    private String getStatus(OrderTihuoModule tihuoModule) {
        if (tihuoModule.isShoppingCard()) {
            switch (tihuoModule.getCardStatus()) {
                case "20":
                    return "充值中";
                case "30":
                    return "已充值";

            }
        } else {
            switch (tihuoModule.getCardStatus()) {
                case "10":
                    return "待发货";
                case "20":
                    return "发货中";
                case "30":
                    return "已发货";
                case "40":
                    return "已提货";

            }
        }
        return "未知";
    }

    public static class TihuoHolder extends BaseViewHolder {
        TextView tvCode, tvDate, tvCardName, tvCount, tvStatus, tvMsg, tvAllPrice;
        ImageView ivCard;

        public TihuoHolder(View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCardName = itemView.findViewById(R.id.tv_card_name);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvAllPrice = itemView.findViewById(R.id.tv_all_price);
            ivCard = itemView.findViewById(R.id.iv_card);
        }
    }
}
