package com.cardshop.cardshop.Contract;

import android.content.Intent;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface PersonalContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void vertifyPayPassword(String psw);

        public abstract void dealFromCamera(Intent intent);

        public abstract void dealFromPhote(Intent intent);

        public abstract void checkAvatarPermisstion();

        public abstract void bindWechat();
    }

    interface IView extends IBaseView<PersonalContract.IPresenter> {
        void setAvatar(String url);

        void setPhone(String phone);

        void getVertifyPswResult(boolean result);

        void setUserName(String userName);

        void setWx(String wx);

        void setQQ(String qq);

        void showAvatarDialog();

        void setCanBindWx(boolean canBindWx);
    }
}
