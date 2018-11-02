package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.WithdrawContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.BalanceModule;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawPresenterImpl extends WithdrawContract.IPresenter {
    private WithdrawContract.IView mView;
    CardModule cardModule = null;
    private Context context;

    public WithdrawPresenterImpl(WithdrawContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        mView.initBanalce(UserModule.getCurrentUser().getMember().getAvailableRcBalance() + "");
        CardModule.getCardList(UserModule.getCurrentUser().getMember().getMemberId(), new Callback<ResponseData<List<CardModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<CardModule>>> call, Response<ResponseData<List<CardModule>>> response) {
                if (response.body().isSuccess()) {
                    if (response.body().getData().size() >= 1) {
                        mView.initCard(response.body().getData().get(0), getAvatarId(response.body().getData().get(0)));
                        cardModule = response.body().getData().get(0);
                    } else {
                        mView.initCard(null, getAvatarId(null));
                    }
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<CardModule>>> call, Throwable t) {

            }
        });
    }

    private int getAvatarId(CardModule cardModule) {
        if (null == cardModule)
            return R.mipmap.icon_avatar_tongyong;
        int id;
        switch (CardModule.CardType.getType(cardModule.getPdcBankName())) {
            case ZGYH:
                id = R.mipmap.icon_avatar_zhongguo;
                break;
            case GSYH:
                id = R.mipmap.icon_avatar_gongshang;
                break;
            case JSYH:
                id = R.mipmap.icon_avatar_jianshe;
                break;
            case JTYH:
                id = R.mipmap.icon_avatar_jiaotong;
                break;
            case NYYH:
                id = R.mipmap.icon_avatar_nongye;
                break;
            case ZSYH:
                id = R.mipmap.icon_avatar_zhaoshang;
                break;
            default:
                id = R.mipmap.icon_avatar_tongyong;
                break;
        }
        return id;
    }

    @Override
    public void withdrawAll() {
        mView.onWithDrawAll(UserModule.getCurrentUser().getMember().getAvailableRcBalance() + "");
    }

    @Override
    public void chooseCard(CardModule cardModule) {
        mView.initCard(cardModule, getAvatarId(cardModule));
        this.cardModule = cardModule;
    }

    @Override
    public void withdraw(String amount) {
        if (null == cardModule) {
            mView.showSnackerToast("请选择银行卡");
        } else {
            BalanceModule.widthDraw(amount, cardModule.getPdcId() + "", new Callback<ResponseData<BaseModule>>() {
                @Override
                public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                    if (response.body().isSuccess()) {
                        mView.showToast("提现成功");
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
    }

    @Override
    public void checkPayPassword(final String amount, String psw) {
        UserModule.vertifyPayPassword(psw, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (response.body().isSuccess()) {
                    withdraw(amount);
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {

            }
        });
    }
}
