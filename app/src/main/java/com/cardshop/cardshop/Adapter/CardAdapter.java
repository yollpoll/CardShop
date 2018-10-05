package com.cardshop.cardshop.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class CardAdapter extends BaseFooterAdapter<List<CardModule>> {
    private List<CardModule> list;
    private OnItemClickListener onItemClickListener;

    public CardAdapter(List<CardModule> list, OnItemClickListener onItemClickListener) {
        super(list);
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, final int position) {

        super.onBindContentViewHolder(holder, position);
        ViewHolder viewHolder = (ViewHolder) holder;
        CardModule cardModule = list.get(position);
        viewHolder.tvCardName.setText(cardModule.getPdcBankName());
        viewHolder.tvCardCode.setText(changeCode(cardModule.getPdcBankNo()));
        viewHolder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(list.get(position));
            }
        });
        Drawable drawable = null;
        switch (CardModule.CardType.getType(cardModule.getPdcBankName())) {
            case ZGYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_zhongguo);
                break;
            case GSYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_gongshang);
                break;
            case JSYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_jianshe);
                break;
            case JTYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_jiaotong);
                break;
            case NYYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_nongye);
                break;
            case ZSYH:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_zhaoshang);
                break;
            default:
                drawable = getContext().getResources().getDrawable(R.mipmap.icon_tongyong);
                break;
        }
        if (null != drawable)
            viewHolder.rlRoot.setBackground(drawable);
    }

    private String changeCode(String code) {
        return code.substring(12);
    }

    public static class ViewHolder extends BaseViewHolder {
        private RelativeLayout rlRoot;
        private TextView tvCardName, tvCardCode;

        public ViewHolder(View itemView) {
            super(itemView);
            rlRoot = itemView.findViewById(R.id.rl_root);
            tvCardName = itemView.findViewById(R.id.tv_card_name);
            tvCardCode = itemView.findViewById(R.id.tv_card_code);
        }
    }

    public interface OnItemClickListener {
        void onClick(CardModule cardModule);
    }
}
