package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Contract.AboutUsContact;
import com.cardshop.cardshop.Utils.APKVersionCodeUtils;

public class AboutUsPresenterImpl extends AboutUsContact.Presenter {
    private AboutUsContact.IView mView;
    private Context context;

    public AboutUsPresenterImpl(AboutUsContact.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        mView.setVersion("v."+APKVersionCodeUtils.getVerName(context));
    }
}
