package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.FileUtils;
import com.cardshop.cardshop.Utils.PermissionUtils;
import com.cardshop.cardshop.Utils.Tools;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalMsgContractImpl extends PersonalContract.IPresenter<PersonalContract.IView> {
    public static final int REQUEST_AVARAR = 1;
    private PersonalContract.IView mView;
    private Context context;
    private String[] avatarPermisssion = new String[]{PermissionUtils.WRITE_EXTERNAL_STORAGE,
            PermissionUtils.READ_EXTERNAL_STORAGE, PermissionUtils.CAMERA};

    public PersonalMsgContractImpl(PersonalContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        mView.setAvatar(UserModule.getCurrentUser().getMember().getMemberAvatar());
        mView.setPhone(UserModule.getCurrentUser().getMember().getMemberMobile());
        mView.setUserName(UserModule.getCurrentUser().getMember().getMemberName());
        mView.setWx(UserModule.getCurrentUser().getMember().getWeixinInfo());
        mView.setQQ(UserModule.getCurrentUser().getMember().getMemberQq());
    }

    @Override
    public void vertifyPayPassword(String psw) {
        UserModule.vertifyPayPassword(psw, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    mView.getVertifyPswResult(true);
                } else {
                    mView.showSnackerToast("密码错误");
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.showSnackerToast("访问出错");
            }
        });
    }

    @Override
    public void dealFromCamera(Intent intent) {
        String path = FileUtils.getPathByUri(Tools.filrUri, context);
        File file = new File(path);
        uploadAvatar(file);
    }

    @Override
    public void dealFromPhote(Intent intent) {
        File file;
        Uri uri = intent.getData();
        String path = FileUtils.getPathByUri(uri, context);
        file = new File(path);
        uploadAvatar(file);
    }

    @Override
    public void checkAvatarPermisstion() {
        PermissionUtils.checkAndRequestPermission(mView.getmContext(), avatarPermisssion,
                REQUEST_AVARAR, onPermissionGet);
    }

    private void uploadAvatar(File file) {
        mView.showLoading("上传头像", "上传头像中,请稍等");
        UserModule.setAvatar(file, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (!response.isSuccessful()) {
                    mView.showSnackerToast(response.errorBody() + "");
                    return;
                }
                if (response.body().isSuccess()) {
                    mView.goBack();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.showSnackerToast(t.getMessage());
            }
        });
    }

    private PermissionUtils.OnPermissionGet onPermissionGet = new PermissionUtils.OnPermissionGet() {
        @Override
        public void onGet() {
            mView.showAvatarDialog();
        }
    };
}
