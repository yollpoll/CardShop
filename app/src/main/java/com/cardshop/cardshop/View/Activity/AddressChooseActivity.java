package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
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
    int actionMode = AddressChooseFragment.MODE_CHOOSE;

    public static void gotoAddressChooseActivity(Context context, int actionMode) {
        Intent intent = new Intent(context, AddressChooseActivity.class);
        intent.putExtra("actionMode", actionMode);
        context.startActivity(intent);
    }

    public static void gotoChooseActivity(Activity context, int requestCode) {
        Intent intent = new Intent(context, AddressChooseActivity.class);
        intent.putExtra("actionMode", AddressChooseFragment.MODE_CHOOSE);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        actionMode = getIntent().getIntExtra("actionMode", AddressChooseFragment.MODE_CHOOSE);
        new AddressChoosePresenterImpl((AddressChooseContract.IView) loadBaseFragment(AddressChooseFragment.newInstance()), actionMode);
    }
}
