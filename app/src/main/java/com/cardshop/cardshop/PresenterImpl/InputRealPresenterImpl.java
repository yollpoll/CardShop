package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.InputRealNameContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.RealNameModule;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputRealPresenterImpl extends InputRealNameContract.IPresenter {
    private InputRealNameContract.IView mView;

    public InputRealPresenterImpl(InputRealNameContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void checkInput(String name, String code) {
        if (TextUtils.isEmpty(name)) {
            mView.showSnackerToast("请输入姓名");
        } else if (TextUtils.isEmpty(code)) {
            mView.showSnackerToast("请输入身份证号码");
        } else {
            registerRealName(name, code.toLowerCase());
        }
    }

    private void registerRealName(final String name, final String code) {
        RealNameModule.inputRealName(UserModule.getCurrentUser().getMember().getMemberId(),
                name, code, new Callback<ResponseData<BaseModule>>() {
                    @Override
                    public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                        if (response.body().isSuccess()) {
                            mView.showToast(response.body().getMsg());
                            updateUser(name, code);
                            mView.goBack();
                        } else {
                            mView.showSnackerToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {

                    }
                });
    }

    private void updateUser(String name, String code) {
        UserModule.MemberBean memberBean = UserModule.getCurrentUser().getMember();
        memberBean.setMemberTruename(name);
        memberBean.setMemberIdCard(code);
        UserModule userModule = UserModule.getCurrentUser();
        userModule.setMember(memberBean);
        UserModule.saveToLocal(userModule);
    }
}
