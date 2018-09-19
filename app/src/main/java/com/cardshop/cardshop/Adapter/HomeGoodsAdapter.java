package com.cardshop.cardshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.List;

public class HomeGoodsAdapter extends FooterAdapter<List<GoodsModule.Entity>, BaseViewHolder> {
    private List<GoodsModule.Entity> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public HomeGoodsAdapter(List<GoodsModule.Entity> list, OnItemClickListener onItemClickListener) {
        super(list);
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        GoodsModule.Entity item = list.get(position);
        viewHolder.tvName.setText(item.getGoods_name());
        viewHolder.tvPrice.setText(item.getAll_price());
        viewHolder.tvDiscount.setText(item.getZhuanmai_rate() + "折");
        viewHolder.tvSeller.setText(item.getMember_name() + " 出售");
        viewHolder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
        ImageUtils.loadImage(item.getGoods_image(), ((ViewHolder) holder).ivGoods, context);
    }

    @Override
    protected void onBindFooterViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_adapter, parent, false);
        return new BaseViewHolder(view);
    }

    public static class ViewHolder extends BaseViewHolder {
        ImageView ivGoods;
        TextView tvName, tvPrice, tvDiscount, tvSeller;
        RelativeLayout rlRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGoods = itemView.findViewById(R.id.iv_goods);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDiscount = itemView.findViewById(R.id.tv_discount);
            tvSeller = itemView.findViewById(R.id.tv_seller);
            rlRoot = itemView.findViewById(R.id.rl_root);
        }
    }
}
