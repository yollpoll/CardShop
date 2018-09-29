package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ChangePhonContract;
import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Utils.RxUtils;

public class ChangePhonPresenterImpl extends ChangePhonContract.IPresenter {
    private static final int COUNT_TIME = 60;
    private ChangePhonContract.IView mView;
    private String newPhone;

    public ChangePhonPresenterImpl(ChangePhonContract.IView mView, String newPhone) {
        this.mView = mView;
        this.newPhone = newPhone;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        startCountdown();
        setNewPhone();
    }

    private void startCountdown() {
        RxUtils.startCountDown(60 * 1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                mView.showCountDown(count + "秒后重发");
            }
        });
    }

    private void setNewPhone() {
        String content = "请输入" + newPhone + "收到的验证码";
        mView.setNewPhone(content);
    }

    @Override
    public void stop() {
        super.stop();
        RxUtils.isStopCountDown=true;
    }
}
