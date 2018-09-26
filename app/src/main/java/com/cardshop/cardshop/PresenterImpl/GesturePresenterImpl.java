package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GestureContract;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.cardshop.Widget.GesturePasswordView;

import java.util.ArrayList;
import java.util.List;

public class GesturePresenterImpl extends GestureContract.IPresenter {
    private GestureContract.IView mView;
    private List<Integer> password = new ArrayList<>();

    public GesturePresenterImpl(GestureContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void onCheckPsW(List listPassword) {
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
                mView.onCheckResult(true);
                savePassword();
            } else {
                mView.setTip("两次输入不同,请重新输入");
                password.clear();
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

}
