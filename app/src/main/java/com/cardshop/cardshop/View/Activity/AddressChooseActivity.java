package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.AddressChooseContract;
import com.cardshop.cardshop.PresenterImpl.AddressChoosePresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.AddressChooseFragment;

public class AddressChooseActivity extends BaseActivity {
    public static void gotoAddressChooseActivity(Context context) {
        Intent intent = new Intent(context, AddressChooseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        new AddressChoosePresenterImpl((AddressChooseContract.IView) loadBaseFragment(AddressChooseFragment.newInstance()));
    }
}
