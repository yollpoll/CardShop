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
import com.cardshop.cardshop.Listener.OnItemLongClickListener;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressAdapter extends BaseFooterAdapter<List<AddressModule>> {
    private List<AddressModule> list = new ArrayList<>();
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private boolean isShowArrow = false;

    public MyAddressAdapter(List<AddressModule> addressModules, OnItemClickListener onItemClickListener, boolean isShowArrow) {
        super(addressModules);
        this.onItemClickListener = onItemClickListener;
        this.isShowArrow = isShowArrow;
        this.list = addressModules;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    protected void onBindContentViewHolder(final BaseViewHolder holder, final int position) {
        AddressModule item = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(item.getTrueName());
        viewHolder.tvPhone.setText(item.getMobPhone());
        viewHolder.tvAddress.setText(item.getAreaInfo() + " " + item.getAddress());
        if ("0".equals(item.getIsDefault())) {
            viewHolder.tvDefault.setVisibility(View.GONE);
        } else {
            viewHolder.tvDefault.setVisibility(View.VISIBLE);
        }
        viewHolder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, holder.getAdapterPosition());
            }
        });
        if (isShowArrow) {
            viewHolder.ivArrow.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivArrow.setVisibility(View.GONE);
        }
        if (null != onItemLongClickListener) {
            viewHolder.rlRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onLongClick(view,position);
                    return true;
                }
            });
        }

    }


    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_address, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvName, tvPhone, tvDefault, tvAddress;
        RelativeLayout rlRoot;
        ImageView ivArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvDefault = itemView.findViewById(R.id.tv_default);
            tvAddress = itemView.findViewById(R.id.tv_address);
            rlRoot = itemView.findViewById(R.id.rl_root);
            ivArrow = itemView.findViewById(R.id.iv_arrow);
        }
    }
}
