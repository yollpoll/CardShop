package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AddAddressContract;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.PresenterImpl.AddAddressPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AddAddressFragment;

public class AddAddressActivity extends SimpleActivity {
    AddAddressFragment mFragment;

    public static void gotoAddAddressActivity(Context context) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }

    public static void gotoChangeAddressActivity(Context context, AddressModule addressModule) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        intent.putExtra("address", addressModule);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragment = AddAddressFragment.newInstance();
        AddressModule addressModule = (AddressModule) getIntent().getSerializableExtra("address");
        new AddAddressPresenterImpl((AddAddressContract.IView) loadBaseFragment(mFragment), addressModule);
    }
}
