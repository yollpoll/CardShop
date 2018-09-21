package com.cardshop.cardshop.Adapter.ViewHolder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.R;

public class NoMoreHolder extends BaseViewHolder {
    public TextView tvNoMore;
    public ProgressBar progressBar;
    public NoMoreHolder(View itemView) {
        super(itemView);
        tvNoMore= (TextView) itemView.findViewById(R.id.tv_nomore);
        progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);
    }
}
