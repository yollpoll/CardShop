package com.cardshop.cardshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseViewHolder;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressAdapter extends BaseFooterAdapter<List<AddressModule>> {
    private List<AddressModule> list=new ArrayList<>();
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MyAddressAdapter(List<AddressModule> addressModules, OnItemClickListener onItemClickListener) {
        super(addressModules);
        this.onItemClickListener = onItemClickListener;
        this.list=addressModules;
    }

    @Override
    protected void onBindContentViewHolder(final BaseViewHolder holder,  int position) {
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
    }

//    @Override
//    protected void onBindFooterViewHolder(BaseViewHolder holder, int position) {
//        NoMoreHolder holder1 = (NoMoreHolder) holder;
//        switch (getStatus()) {
//            case FOOTER_TYPE_NOMORE:
//                holder1.tvNoMore.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (null != onNoMoreCliclListener)
//                            onNoMoreCliclListener.onClick();
//                    }
//                });
//                holder1.tvNoMore.setVisibility(View.VISIBLE);
//                holder1.progressBar.setVisibility(View.GONE);
//                break;
//            case FOOTER_TYPE_LOADING:
//                holder1.progressBar.setVisibility(View.VISIBLE);
//                holder1.tvNoMore.setVisibility(View.GONE);
//                break;
//        }
//    }

    @Override
    protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_address, parent, false);
        return new ViewHolder(view);
    }

//    @Override
//    protected BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_adapter, parent, false);
//        return new BaseViewHolder(view);
//    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvName, tvPhone, tvDefault, tvAddress;
        RelativeLayout rlRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvDefault = itemView.findViewById(R.id.tv_default);
            tvAddress = itemView.findViewById(R.id.tv_address);
            rlRoot = itemView.findViewById(R.id.rl_root);
        }
    }
}
