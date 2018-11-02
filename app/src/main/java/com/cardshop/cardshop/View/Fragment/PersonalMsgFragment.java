package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.cardshop.cardshop.PresenterImpl.PersonalMsgContractImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DialogUtils;
import com.cardshop.cardshop.Utils.PermissionUtils;
import com.cardshop.cardshop.Utils.Tools;
import com.cardshop.cardshop.View.Activity.ChangeUserNameActivity;
import com.cardshop.cardshop.View.Activity.InputNewPhoneActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.framework.Utils.ImageUtils;

public class PersonalMsgFragment extends BaseFragment implements PersonalContract.IView, PasswordEditLayout.OnInputCompleteListener {
    public static final int REQUEST_USERNAME = 120;
    public static final int REQUEST_PHONE = 123;

    private PersonalContract.IPresenter presenter;
    private RelativeLayout rlUsername, rlAvatar, rlPhone, rlWechat, rlQQ;
    private TextView tvPhone, tvUsername, tvWx, tvQQ;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PersonalMsgContractImpl.REQUEST_AVARAR:
                if (PermissionUtils.changeRequestResult(grantResults)) {
                    showAvatarDialog();
                } else {
                    showSnackerToast("获取权限失败无法设置头像");
                }
                break;
        }
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
        tvUsername = view.findViewById(R.id.tv_username);
        tvWx = view.findViewById(R.id.tv_wx);
        tvQQ = view.findViewById(R.id.tv_qq);

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
                ChangeUserNameActivity.gotoChangeUserNameActivity(getActivity(), REQUEST_USERNAME);
                break;
            case R.id.rl_phone:
                changePhone();
                break;
            case R.id.rl_avatar:
                presenter.checkAvatarPermisstion();
//                Tools.showChoosePicDialog(getActivity());
                break;
        }
    }

    @Override
    public void setAvatar(String url) {
        ImageUtils.loadCycleImage(url, ivAvatar, R.mipmap.icon_default_avatar, getActivity());
    }

    @Override
    public void setPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void getVertifyPswResult(boolean result) {
        if (result) {
            InputNewPhoneActivity.gotoInputNewActivity(getActivity(),REQUEST_PHONE);
        } else {
            showSnackerToast("验证密码失败");
        }
    }

    @Override
    public void setUserName(String userName) {
        tvUsername.setText(userName);
    }

    @Override
    public void setWx(String wx) {
        tvWx.setText(wx);
    }

    @Override
    public void setQQ(String qq) {
        tvQQ.setText(qq);
    }

    @Override
    public void showAvatarDialog() {
        Tools.showChoosePicDialog(getActivity());
    }

    private void changePhone() {
        payPasswordDialog = DialogUtils.showPayPasswordDialog(getActivity(), this);
    }


    /**
     * 修改手机号输入支付密码以后
     *
     * @param psw
     */
    @Override
    public void onComplete(String psw) {
        if (null != payPasswordDialog)
            payPasswordDialog.dismiss();
        presenter.vertifyPayPassword(psw);
    }

    @Override
    public void onReturnResult(int requestCode, int resultCode, Intent data) {
        super.onReturnResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_USERNAME:
                if (resultCode == Activity.RESULT_OK) {
                    String name = data.getStringExtra("name");
                    tvUsername.setText(name);
                }
                break;
            case Tools.PIC_FROM_PHOTO:
                if (resultCode != Activity.RESULT_OK) {
                    showSnackerToast("获取图片失败");
                    break;
                }
                presenter.dealFromPhote(data);
//                Uri uri = data.getData();
//                String path = FileUtils.getPathByUri(uri, this);
//                file = new File(path);
//                ivAvatar.setImageURI(uri);
                break;
            case Tools.PIC_FROM_CAMERA:
                if (resultCode != Activity.RESULT_OK) {
                    showSnackerToast("拍照失败");
                    break;
                }
                presenter.dealFromCamera(data);
//                path = FileUtils.getPathByUri(Utils.filrUri, this);
//                ivAvatar.setImageURI(Utils.filrUri);
//                file = new File(path);
                break;
            case REQUEST_PHONE:
                String phone = data.getStringExtra("phone");
                tvPhone.setText(phone);
                break;
        }
    }
}
