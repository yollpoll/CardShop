package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Contract.GestureContract;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.cardshop.Widget.GesturePasswordView;

import java.util.ArrayList;
import java.util.List;

public class GesturePresenterImpl extends GestureContract.IPresenter {
    private static final int FAULT_TIMES = 5;
    private Context context;
    private GestureContract.IView mView;
    private List<Integer> password = new ArrayList<>();
    private boolean isSetPasw = true;
    private int faultTimes = 0;

    public GesturePresenterImpl(GestureContract.IView mView, boolean isSetPasw) {
        this.mView = mView;
        this.isSetPasw = isSetPasw;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        mView.setPsw(isSetPasw);
    }

    @Override
    public void onCheckPsW(List listPassword) {
        if (isSetPasw) {
            if (password.size() == 0) {
                //还没有存密码
                mView.setTip("再输入一次");
                if (listPassword.size() >= 4) {
                    for (Object gesturePasswordView : listPassword) {
                        password.add(((GesturePasswordView) gesturePasswordView).getId());
                    }
                } else {
                    mView.setTip("至少设置四个点");
                }
            } else {
                if (checkPassword(listPassword)) {
                    //验证通过
//                    mView.onCheckResult(true);
                    savePassword();
                } else {
                    mView.setTip("两次输入不同,请重新输入");
                    password.clear();
                }
            }

        } else {
            //登陆模式
            String psw = SPUtiles.getGesture();
            String pswStr = "";
            for (Object gesturePasswordView : listPassword) {
                pswStr += ((GesturePasswordView) gesturePasswordView).getId() + ",";
            }
            if (psw.equalsIgnoreCase(pswStr)) {
                mView.onCheckResult(psw.equals(pswStr));
            } else {
                if (FAULT_TIMES - faultTimes > 0) {
                    faultTimes++;
                    mView.setTip("还能输入" + (FAULT_TIMES - faultTimes + 1) + "次");
                } else {
                    //锁定
                    mView.onCheckResult(false);
//                    LoginActivity.gotoLoginActivity(context);
                }
            }
        }
    }

    /**
     * 检验密码
     *
     * @return
     */
    private boolean checkPassword(List<GesturePasswordView> checkPassword) {
        if (password.size() == checkPassword.size()) {
            for (int i = 0; i < password.size(); i++) {
                if (password.get(i) != checkPassword.get(i).getId()) {
                    //两次输入不同
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void savePassword() {
        if (password.size() != 0) {
            String passwordStr = "";
            for (Integer p : password) {
                passwordStr += p + ",";
            }
            saveGesturePassword(passwordStr);
            mView.goBack();
            mView.showToast("新建手势密码成功");
            SPUtiles.saveIsOpenGesture(true);
        }
    }

    //根据账号保存手势密码
    public static void saveGesturePassword(String psd) {
        SPUtiles.saveGesture(psd);
    }

    @Override
    public void logout() {
        UserModule.logout();
    }
}
