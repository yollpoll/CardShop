package com.cardshop.cardshop.PresenterImpl;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.cardshop.cardshop.Contract.LoginContract;

public class LoginPresenterImpl extends LoginContract.IPresenter<LoginContract.IView>  {
    private LoginContract.IView mView;
    private String userName, password;
    Handler handler;


    public LoginPresenterImpl(LoginContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void login(String userName, String password) {
        Log.d("spq", "userName>>>" + userName + " password>>>>" + password);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.onLoginResult(true, "success");
            }
        }, 3000);

    }

    @Override
    public void checkNull(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mView.setBtnLoginEnable(false);
        } else {
            mView.setBtnLoginEnable(true);
        }
    }

    @Override
    public void start() {
        handler=new Handler();
    }
}
