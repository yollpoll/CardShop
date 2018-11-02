package com.cardshop.cardshop.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.SettingContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.AboutUsActivity;
import com.cardshop.cardshop.View.Activity.ForgetPasswordActivity;
import com.cardshop.cardshop.View.Activity.GestureActivity;
import com.cardshop.cardshop.View.Activity.LoginActivity;
import com.cardshop.cardshop.View.Activity.ProtocolActivity;
import com.cardshop.cardshop.View.Activity.SetPayPasswordActivity;

public class SettingFragment extends BaseFragment implements SettingContract.IView {
    private SettingContract.IPresenter presenter;
    private RelativeLayout rlPassword, rlPayPassword, rlFaceId, rlGesture;
    private RelativeLayout rlAoutUs, rlServiceProtocol, rlClearCache;
    private RelativeLayout rlLogout;
    private TextView tvCache;
    private Switch switchFaceId, switchGesture;
    private boolean init = true;//这个是表示是否是初始化的时候，防止进去就出发switch的事件

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(SettingContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rlPassword = view.findViewById(R.id.rl_password);
        rlPayPassword = view.findViewById(R.id.rl_pay_password);
        rlFaceId = view.findViewById(R.id.rl_face_id);
        rlGesture = view.findViewById(R.id.rl_gesture);
        rlAoutUs = view.findViewById(R.id.rl_about_us);
        rlServiceProtocol = view.findViewById(R.id.rl_service_protocol);
        rlClearCache = view.findViewById(R.id.rl_clear_cache);
        rlLogout = view.findViewById(R.id.rl_logout);
        switchGesture = view.findViewById(R.id.switch_gesture);
        tvCache = view.findViewById(R.id.tv_cache);

        rlPassword.setOnClickListener(this);
        rlPayPassword.setOnClickListener(this);
        rlFaceId.setOnClickListener(this);
        rlGesture.setOnClickListener(this);
        rlAoutUs.setOnClickListener(this);
        rlServiceProtocol.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("设置");
        showBack();
        setNoStatusBar();
        init = true;
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//            if (init) {
//                init = false;
//                return;
//            }
            if (b) {
                //开启
                GestureActivity.gotoGestureActivity(getActivity(), true);
            } else {
                presenter.openGesture(false);
            }
        }
    };

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_password:
                ForgetPasswordActivity.gotoForgetPasswordActivity(getActivity(), "登录密码设置");
                break;
            case R.id.rl_pay_password:
                SetPayPasswordActivity.gotoSetPayPasswordActivity(getActivity());
                break;
            case R.id.rl_clear_cache:
                presenter.clearCache(getActivity());
                break;
            case R.id.rl_about_us:
                AboutUsActivity.gotoAboutUs(getActivity());
                break;
            case R.id.rl_logout:
                presenter.logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
                goBack();
                break;
            case R.id.rl_service_protocol:
                ProtocolActivity.gotoProtocolActivity(getActivity(),"服务协议",R.raw.service_protocol);
                break;
        }
    }

    @Override
    public void openGesture(boolean open) {
        switchGesture.setChecked(open);
        switchGesture.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void setCache(String content) {
        tvCache.setText(content);
    }
}
