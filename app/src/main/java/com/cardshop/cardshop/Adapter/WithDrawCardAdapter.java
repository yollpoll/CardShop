package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class WithDrawCardAdapter extends BaseFooterAdapter<List<CardModule>> {
    private OnItemClickListener onItemClickListener;

    public WithDrawCardAdapter(List<CardModule> list, OnItemClickListener onItemClickListener) {
        super(list);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_withdraw_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {
        super.onBindContentViewHolder(holder, position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvCardCode.setText("尾号" + data.get(position).getPdcBankNo().substring(data.get(position).getPdcBankNo().length() - 4));
        viewHolder.tvCardName.setText(data.get(position).getPdcBankName());
        viewHolder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, position);
            }
        });
        int id;
        switch (CardModule.CardType.getType(data.get(position).getPdcBankName())) {
            case ZGYH:
                id = R.mipmap.icon_avatar_zhongguo;
                break;
            case GSYH:
                id = R.mipmap.icon_avatar_gongshang;
                break;
            case JSYH:
                id = R.mipmap.icon_avatar_jianshe;
                break;
            case JTYH:
                id = R.mipmap.icon_avatar_jiaotong;
                break;
            case NYYH:
                id = R.mipmap.icon_avatar_nongye;
                break;
            case ZSYH:
                id = R.mipmap.icon_avatar_zhaoshang;
                break;
            default:
                id = R.mipmap.icon_avatar_tongyong;
                break;
        }
        ((ViewHolder) holder).ivCard.setImageResource(id);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvCardName;
        TextView tvCardCode;
        RelativeLayout rlRoot;
        ImageView ivCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCardName = itemView.findViewById(R.id.tv_card_name);
            tvCardCode = itemView.findViewById(R.id.tv_card_code);
            rlRoot = itemView.findViewById(R.id.rl_root);
            ivCard = itemView.findViewById(R.id.iv_card);
        }
    }
}
