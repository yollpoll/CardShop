package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.R;

public class PersonalMsgFragment extends BaseFragment implements PersonalContract.IView {
    private PersonalContract.IPresenter presenter;
    private RelativeLayout rlUsername, rlAvatar, rlPhone, rlWechat, rlQQ;
    private TextView tvPhone;
    private ImageView ivAvatar;

    public static PersonalMsgFragment newInstance() {
        return new PersonalMsgFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(PersonalContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_msg, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("个人资料");
        showBack();
        setNoStatusBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rlUsername = view.findViewById(R.id.rl_username);
        rlAvatar = view.findViewById(R.id.rl_avatar);
        rlPhone = view.findViewById(R.id.rl_phone);
        rlWechat = view.findViewById(R.id.rl_wechat);
        rlQQ = view.findViewById(R.id.rl_qq);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvPhone = view.findViewById(R.id.tv_phone);

        rlUsername.setOnClickListener(this);
        rlAvatar.setOnClickListener(this);
        rlPhone.setOnClickListener(this);
        rlWechat.setOnClickListener(this);
        rlQQ.setOnClickListener(this);

    }

}
