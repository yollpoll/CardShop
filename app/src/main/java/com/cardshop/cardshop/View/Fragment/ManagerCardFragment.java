package com.cardshop.cardshop.View.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ManagerCardContract;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.R;

public class ManagerCardFragment extends BaseFragment implements ManagerCardContract.IView {
    private ManagerCardContract.IPresenter presenter;

    private TextView tvCardName, tvCardCode, tvDelCard;
    private RelativeLayout rlRoot;


    public static ManagerCardFragment newInstance() {
        return new ManagerCardFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(ManagerCardContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_managercard, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("银行卡管理");
        showBack();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvCardName = view.findViewById(R.id.tv_card_name);
        tvCardCode = view.findViewById(R.id.tv_card_code);
        rlRoot = view.findViewById(R.id.rl_root);
        tvDelCard = view.findViewById(R.id.tv_del_card);
        tvDelCard.setOnClickListener(this);
    }

    @Override
    public void setCard(CardModule card) {
        tvCardName.setText(card.getPdcBankName());
        rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        Drawable drawable = null;
        switch (CardModule.CardType.getType(card.getPdcBankName())) {
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
            rlRoot.setBackground(drawable);
        tvCardCode.setText(card.getPdcBankNo());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_del_card:
                presenter.delCard();
                break;
        }
    }
}
