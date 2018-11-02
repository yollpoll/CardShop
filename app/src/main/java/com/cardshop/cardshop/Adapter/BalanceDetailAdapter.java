package com.cardshop.cardshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Module.BalanceModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DateUtils;

import java.util.List;

public class BalanceDetailAdapter extends BaseFooterAdapter<List<BalanceModule>> {

    public BalanceDetailAdapter(List<BalanceModule> balanceModules) {
        super(balanceModules);
    }

    @Override
    protected void onBindContentViewHolder(BaseViewHolder holder, int position) {
        super.onBindContentViewHolder(holder, position);
        BalanceModule module = data.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitle.setText(module.getLgDesc());
        viewHolder.tvDate.setText(DateUtils.getDate(Long.parseLong(module.getLgAddTime())  * 1000));
        if (module.getLgAvAmount() >= 0) {
            viewHolder.tvMoney.setText("+" + module.getLgAvAmount() + "");
            viewHolder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.tvMoney.setText(module.getLgAvAmount() + "");
            viewHolder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.black));
        }

    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balance_detail, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvTitle, tvDate, tvMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMoney = itemView.findViewById(R.id.tv_money);
        }
    }
}
