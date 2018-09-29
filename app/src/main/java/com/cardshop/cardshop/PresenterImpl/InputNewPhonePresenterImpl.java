package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Contract.InputNewPhoneContract;
import com.cardshop.cardshop.Module.UserModule;

public class InputNewPhonePresenterImpl extends InputNewPhoneContract.IPresenter {
    private final String CURRENT_PHONE = "更换手机号后，下次可使用新手机号登录\n当前手机号:";
    private InputNewPhoneContract.IView mView;

    public InputNewPhonePresenterImpl(InputNewPhoneContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        setCurrentPhone();
    }

    private void setCurrentPhone() {
        String phone = UserModule.getCurrentUser().getMember().getMemberMobile();
        String content = CURRENT_PHONE + phone;
        mView.setCurrentPhone(content, CURRENT_PHONE.length());
    }

    @Override
    public void checkInput(String input) {
        if (TextUtils.isEmpty(input)) {
            mView.onCheckInput(false, "新手机号不能为空");
        } else {
            mView.onCheckInput(true, "");
        }
    }
}
