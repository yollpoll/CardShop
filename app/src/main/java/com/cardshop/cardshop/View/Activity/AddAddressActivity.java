package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AddAddressContract;
import com.cardshop.cardshop.PresenterImpl.AddAddressPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AddAddressFragment;

public class AddAddressActivity extends SimpleActivity {
    public static void gotoAddAddressActivity(Context context){
        Intent intent=new Intent(context,AddAddressActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initData() {
        super.initData();
        new AddAddressPresenterImpl((AddAddressContract.IView) loadBaseFragment(AddAddressFragment.newInstance()));
    }
}
