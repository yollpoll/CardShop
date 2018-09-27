package com.cardshop.cardshop.View.Fragment;

import android.app.Dialog;
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
import com.cardshop.cardshop.Utils.DialogUtils;
import com.cardshop.cardshop.View.Activity.ChangeUserNameActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.framework.Utils.ImageUtils;

public class PersonalMsgFragment extends BaseFragment implements PersonalContract.IView, PasswordEditLayout.OnInputCompleteListener {
    private PersonalContract.IPresenter presenter;
    private RelativeLayout rlUsername, rlAvatar, rlPhone, rlWechat, rlQQ;
    private TextView tvPhone;
    private ImageView ivAvatar;
    private Dialog payPasswordDialog;

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

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_username:
                ChangeUserNameActivity.gotoChangeUserNameActivity(getActivity());
                break;
            case R.id.rl_phone:
                changePhone();
                break;
        }
    }

    @Override
    public void setAvatar(String url) {
        ImageUtils.loadCycleImage(url, ivAvatar, getActivity());
    }

    @Override
    public void setPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void getVertifyPswResult(boolean result) {
        if (result) {
            showSnackerToast("验证密码成功");
        } else {
            showSnackerToast("验证密码失败");
        }
    }

    private void changePhone() {
        payPasswordDialog=DialogUtils.showPayPasswordDialog(getActivity(), this);
    }


    /**
     * 修改手机号输入支付密码以后
     *
     * @param psw
     */
    @Override
    public void onComplete(String psw) {
        if(null!=payPasswordDialog)
            payPasswordDialog.dismiss();
        presenter.vertifyPayPassword(psw);
    }
}
