package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.MineContract;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.AddressChooseActivity;
import com.cardshop.cardshop.View.Activity.BalanceActivity;
import com.cardshop.cardshop.View.Activity.CardActivity;
import com.cardshop.cardshop.View.Activity.PersonalMsgActivity;
import com.cardshop.cardshop.View.Activity.SettingActivity;
import com.cardshop.framework.Utils.ImageUtils;
import com.cardshop.framework.Utils.ScreenUtils;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;

public class MineFragment extends BaseFragment implements MineContract.IView {
    public static final float ALL_OFFSET = 470;
    private MineContract.IPresenter presenter;

    private AppBarLayout mAppBarLayout;
    private ImageView ivSetting, ivAvatar;
    private TextView tvUserName, tvPhone, tvBalance, tvCard;
    private RelativeLayout rlBalance, rlCard, rlAddress, rlRealMsg, rlService,
            rlHelp, rlUserMsg;

    private int avatarSize = 0;
    private float appBarScrollRange=ALL_OFFSET;


    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }


    @Override
    public void setPresenter(MineContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.updateUserInfo();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppBarLayout = view.findViewById(R.id.app_bar);
        ivSetting = view.findViewById(R.id.iv_setting);
        ivAvatar = view.findViewById(R.id.iv_avater);
        tvUserName = view.findViewById(R.id.tv_username);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvBalance = view.findViewById(R.id.tv_balance);
        tvCard = view.findViewById(R.id.tv_card);
        rlBalance = view.findViewById(R.id.rl_balance);
        rlCard = view.findViewById(R.id.rl_card);
        rlAddress = view.findViewById(R.id.rl_address);
        rlRealMsg = view.findViewById(R.id.rl_real_msg);
        rlService = view.findViewById(R.id.rl_service);
        rlHelp = view.findViewById(R.id.rl_help);
        rlUserMsg = view.findViewById(R.id.rl_user_msg);

        ivSetting.setOnClickListener(this);
        rlBalance.setOnClickListener(this);
        rlCard.setOnClickListener(this);
        rlAddress.setOnClickListener(this);
        rlRealMsg.setOnClickListener(this);
        rlService.setOnClickListener(this);
        rlHelp.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        avatarSize = ScreenUtils.calculateDpToPx(75, getActivity());
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                appBarScrollRange=mAppBarLayout.getTotalScrollRange();
                float rate = 1 - ((-verticalOffset) / Math.abs(appBarScrollRange));
                ViewGroup.LayoutParams params = ivAvatar.getLayoutParams();
                params.width = (int) (avatarSize * rate);
                params.height = (int) (avatarSize * rate);
                ivAvatar.setLayoutParams(params);
                rlUserMsg.setAlpha(rate);

            }

        });
    }

    @Override
    public void setUserData(UserModule userData) {
        tvUserName.setText(userData.getMember().getMemberName());
        tvPhone.setText(userData.getMember().getMemberMobile());
        tvBalance.setText(userData.getMember().getAvailableRcBalance() + "");
        tvCard.setText(userData.getCardNum() + "");
        ImageUtils.loadCycleImage(userData.getMember().getMemberAvatar(), ivAvatar,R.mipmap.icon_default_avatar, ivAvatar.getContext());
    }

    @Override
    public void gotoService(String title, ConsultSource source) {
        Unicorn.openServiceActivity(getActivity(), title, source);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_address:
                AddressChooseActivity.gotoAddressChooseActivity(getActivity(), AddressChooseFragment.MODE_EDIT);
                break;
            case R.id.iv_setting:
                SettingActivity.gotoSettingActivity(getActivity());
                break;
            case R.id.rl_balance:
                BalanceActivity.gotoBalanceActivity(getActivity());
                break;
            case R.id.rl_card:
                CardActivity.gotoCardActivity(getActivity());
                break;
            case R.id.iv_avater:
                PersonalMsgActivity.gotoPersonalMsgActivity(getActivity());
                break;
            case R.id.rl_service:
                presenter.gotoCustomerService();
                break;
            case R.id.rl_real_msg:
                presenter.gotoRealName();
                break;
        }
    }
}
