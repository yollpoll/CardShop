package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Module.WxUserInfoModule;
import com.cardshop.cardshop.Utils.FileUtils;
import com.cardshop.cardshop.Utils.PermissionUtils;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.cardshop.Utils.Tools;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

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
        mView.setWx(TextUtils.isEmpty(UserModule.getCurrentUser().getMember().getMemberWxopenid()) ? "未绑定" : "已绑定");
//        mView.setWx(UserModule.getCurrentUser().getMember().getWeixinInfo());
        mView.setQQ(UserModule.getCurrentUser().getMember().getMemberQq());

        mView.setCanBindWx(!TextUtils.isEmpty(UserModule.getCurrentUser().getMember().getMemberWxopenid()));
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

    @Override
    public void bindWechat() {
        // send oauth request
        IWXAPI api = ((BaseApplication) BaseApplication.getInstance()).getIwxapi();
        if (null == api)
            return;
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
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

    @Override
    public void onResume() {
        super.onResume();
        if (!SPUtiles.isLoginViaWx())
            return;
        final WxUserInfoModule wxUserInfoModule = WxUserInfoModule.getLocalUserInfo();
        if (null == wxUserInfoModule)
            return;
        mView.showLoading("绑定微信", "绑定微信中,请稍等");
        UserModule.bindWx(wxUserInfoModule, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (response.body().isSuccess()) {
                    mView.setWx("已绑定");
                    mView.setCanBindWx(true);
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
        SPUtiles.saveIsLoginViaWx(false);
    }

    private PermissionUtils.OnPermissionGet onPermissionGet = new PermissionUtils.OnPermissionGet() {
        @Override
        public void onGet() {
            mView.showAvatarDialog();
        }
    };
}
