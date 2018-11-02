package com.cardshop.cardshop.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.List;

public class HomeGoodsAdapter extends BaseFooterAdapter<List<GoodsModule>> {
    private List<GoodsModule> list;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnBuyClickListener onBuyClickListener;

    public HomeGoodsAdapter(List<GoodsModule> list, OnItemClickListener onItemClickListener, OnBuyClickListener onBuyClickListener) {
        super(list);
        this.onBuyClickListener = onBuyClickListener;
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        GoodsModule item = list.get(position);
        viewHolder.tvName.setText(item.getGoodsName());
        viewHolder.tvPrice.setText(item.getAllPrice() + "");
        viewHolder.tvDiscount.setText(item.getZhuanmaiRate() + "折");
        viewHolder.tvSeller.setText(item.getMemberName() + " 出售");
        viewHolder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
        viewHolder.tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBuyClickListener.onClick(position);
            }
        });
        ImageUtils.loadImage(item.getGoodsImage(), ((ViewHolder) holder).ivGoods, context);
    }

    @Override
    protected void onBindFooterViewHolder(BaseViewHolder holder, int position) {
        FooterViewHolder footerViewHolder= (FooterViewHolder) holder;
        if(getStatus()==FOOTER_TYPE_LOADING){
            footerViewHolder.progressBar.setVisibility(View.VISIBLE);
            footerViewHolder.tvNoMore.setVisibility(View.GONE);
            footerViewHolder.cardRoot.setVisibility(View.VISIBLE);
        }else {
            footerViewHolder.progressBar.setVisibility(View.GONE);
            footerViewHolder.tvNoMore.setVisibility(View.VISIBLE);
            footerViewHolder.cardRoot.setVisibility(View.GONE);
        }
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_goods, parent, false);
        return new FooterViewHolder(view);
    }

    public static class ViewHolder extends BaseViewHolder {
        ImageView ivGoods;
        TextView tvName, tvPrice, tvDiscount, tvSeller;
        RelativeLayout rlRoot;
        TextView tvBuy;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGoods = itemView.findViewById(R.id.iv_goods);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDiscount = itemView.findViewById(R.id.tv_discount);
            tvSeller = itemView.findViewById(R.id.tv_seller);
            rlRoot = itemView.findViewById(R.id.rl_root);
            tvBuy = itemView.findViewById(R.id.tv_buy);
        }
    }

    public static class FooterViewHolder extends BaseViewHolder {
        TextView tvNoMore;
        ProgressBar progressBar;
        CardView cardRoot;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tvNoMore = itemView.findViewById(R.id.tv_nomore);
            progressBar = itemView.findViewById(R.id.progressBar);
            cardRoot=itemView.findViewById(R.id.card_root);
        }
    }

    public interface OnBuyClickListener {
        void onClick(int position);
    }
}
