package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Contract.MineContract;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.RealNameActivity;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.UICustomization;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;

public class MinePresenterImpl extends MineContract.IPresenter<MineContract.IView> {
    public static final String SERVICE_URL = "com.cardshop.cardshop.PresenterImpl";
    public static final String SOURCE_TITLE = "我的";

    private MineContract.IView mView;
    private Context context;

    public String serviceTitle = "客服";
    private UserModule userModule;

    public MinePresenterImpl(MineContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        userModule = UserModule.getCurrentUser();
        mView.setUserData(userModule);
        initQiyu();
    }

    @Override
    public void gotoCustomerService() {
        ConsultSource source = new ConsultSource(SERVICE_URL, SOURCE_TITLE, "custom information string");
        mView.gotoService(serviceTitle, source);
    }

    @Override
    public void gotoRealName() {
        //实名
        UserModule userModule = UserModule.getCurrentUser();
        if (null != userModule.getMember().getIsVerified()
                && "1".equals(userModule.getMember().getIsVerified())) {
            //已经认证
            RealNameActivity.gotoRealNameActivity(context, true);
        } else {
            RealNameActivity.gotoRealNameActivity(context, false);
        }
    }

    /**
     * 初始化客服
     */
    private void initQiyu() {
        YSFOptions ysfOptions = new YSFOptions();
        UICustomization uiCustomization = new UICustomization();
        uiCustomization.rightAvatar = UserModule.getCurrentUser().getMember().getMemberAvatar();
        uiCustomization.titleBackgroundResId = R.drawable.shape_title;
        uiCustomization.titleCenter = true;
        uiCustomization.titleBarStyle = 0;
        ysfOptions.uiCustomization = uiCustomization;
        Unicorn.updateOptions(ysfOptions);
    }
}
